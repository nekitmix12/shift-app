package nekit.corporation.shift_app.data.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nekit.corporation.shift_app.data.local_data_source.dbo.UserDbo


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(users: List<UserDbo>)

    @Query("""SELECT * FROM user""")
    suspend fun loadUsers():List<UserDbo>

    @Query("""DELETE FROM user""")
    suspend fun deleteAll()
}