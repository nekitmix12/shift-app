package nekit.corporation.shift_app.domain

import nekit.corporation.shift_app.models.User

interface Repository {

    suspend  fun loadUsers(): List<User>

    suspend fun getUsers(): List<User>

    suspend fun saveUser(users: List<User>)

    suspend fun cleanUsers()
}