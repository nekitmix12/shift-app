package nekit.corporation.shift_app.data

import nekit.corporation.shift_app.data.local_data_source.UserDao
import nekit.corporation.shift_app.data.remote_data_source.UserApi
import nekit.corporation.shift_app.domain.Repository
import nekit.corporation.shift_app.models.User
import nekit.corporation.shift_app.models.toUser
import nekit.corporation.shift_app.models.toUserDbo
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
) : Repository {
    override suspend fun getUsers(): List<User> = userApi.getUser().results
    override suspend fun loadUsers(): List<User> = userDao.loadUsers().map { it.toUser() }
    override suspend fun saveUser(users: List<User>) =
        userDao.saveUsers(users.map { it.toUserDbo() })

    override suspend fun cleanUsers() = userDao.deleteAll()
}