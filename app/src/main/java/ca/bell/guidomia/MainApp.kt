package ca.bell.guidomia

import android.app.Application
import ca.bell.guidomia.di.dataModule
import ca.bell.guidomia.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(viewModelModule, dataModule)
        }
    }
}