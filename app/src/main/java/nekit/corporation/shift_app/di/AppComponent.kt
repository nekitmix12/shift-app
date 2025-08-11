package nekit.corporation.shift_app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import nekit.corporation.shift_app.MainActivity
import nekit.corporation.shift_app.data.local_data_source.DataBaseModule
import nekit.corporation.shift_app.data.remote_data_source.NetworkModule

@Component(modules = [NetworkModule::class, ViewModelModule::class, AppBindModule::class, DataBaseModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    fun interface Factory {
        operator fun invoke(@BindsInstance context: Context): AppComponent
    }
}