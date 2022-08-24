package ca.bell.guidomia

import android.app.Application
import ca.bell.guidomia.di.DaggerAppComponent

class MainApp : Application() {

    val appComponent by lazy { DaggerAppComponent.factory().create(this) }

    override fun onCreate() {
        super.onCreate()
        INSTANC = this
    }

    companion object {
        private lateinit var INSTANC: MainApp

        fun get() = INSTANC
    }
}