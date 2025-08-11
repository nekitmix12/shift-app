package nekit.corporation.shift_app.di

import android.app.Application

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().invoke(this)
    }
}