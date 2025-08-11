package nekit.corporation.shift_app.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nekit.corporation.shift_app.domain.Repository
import nekit.corporation.shift_app.models.ListModel
import nekit.corporation.shift_app.models.User
import java.util.UUID
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _users = MutableStateFlow<ImmutableList<ListModel>>(persistentListOf())
    val users: StateFlow<ImmutableList<ListModel>> = _users

    private var _showScroll = MutableStateFlow(false)
    val showScroll: StateFlow<Boolean> = _showScroll


    private var storage = mutableMapOf<UUID, User>()

    init {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                var result = repository.loadUsers()
                if (result.isEmpty()) {
                    result = repository.getUsers()
                    repository.cleanUsers()
                    repository.saveUser(result)
                }
                update(result)
            } catch (e: Exception) {
                Log.e("ListViewModel", e.toString())
            }

        }

    }

    fun update() {
        viewModelScope.launch {
            Log.d("ListViewModel", "update")
            update(repository.getUsers())
        }
    }

    private suspend fun update(elements: List<User>) {
        Log.d("ListViewModel", elements.toString())
        _showScroll.value = true
        val usersTemp = mutableListOf<ListModel>()
        val usersStorage = mutableMapOf<UUID, User>()
        elements.forEach {
            val uuid = UUID.randomUUID()
            usersStorage[uuid] = it
            usersTemp.add(ListModel(uuid, it.name, it.picture.medium, it.location, it.phone))
        }
        _users.value = usersTemp.toImmutableList()
        storage = usersStorage
        repository.cleanUsers()
        repository.saveUser(usersStorage.map { it.value })
        delay(500)
        _showScroll.value = false

    }

    fun getUserByUUId(uuid: UUID) = storage[uuid]
}