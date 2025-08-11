package nekit.corporation.shift_app.data.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import nekit.corporation.shift_app.data.local_data_source.dbo.UserDbo

@Database(
    entities = [
        UserDbo::class
    ], version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

}