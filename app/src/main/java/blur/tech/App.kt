package blur.tech


import android.app.Application
import android.content.Context
import blur.tech.candidate.core.AppComponent
import blur.tech.candidate.core.DaggerAppComponent
import blur.tech.candidate.core.modules.SharedPreferencesModule

class App : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .sharedPreferencesModule(SharedPreferencesModule(this))
            .build()

    }

    fun getAppComponent(): AppComponent {
//        if (::component.isInitialized){
//            component = DaggerAppComponent.builder()
//                .sharedPreferencesModule(SharedPreferencesModule(this))
//                .build()
//        }
        return component
    }

    companion object {

        private fun getApp(context: Context): App {
            return context.applicationContext as App
        }

        lateinit var INSTANCE: App


    }
}