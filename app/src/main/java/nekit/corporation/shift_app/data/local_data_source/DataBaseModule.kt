package nekit.corporation.shift_app.data.local_data_source

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
object DataBaseModule {
    @Provides
    fun dataBase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "app_database").build()

    @Provides
    fun provideUserDao(roomDatabase: AppDataBase) = roomDatabase.userDao()
}