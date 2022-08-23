package ca.bell.guidomia.di

import android.content.Context
import ca.bell.guidomia.data.GuidomiaRepository
import ca.bell.guidomia.ui.MainViewModel
import com.google.gson.Gson


object ManualDI {

    lateinit var appContext: Context

    val gson by lazy { Gson() }

    val repository by lazy { GuidomiaRepository(gson, appContext) }

    val mainViewModelFactory: MainViewModel.Factory
        get() = MainViewModel.Factory(repository)


    fun initIfNecessary(appContext: Context) {
        if (this::appContext.isInitialized) return
        this.appContext = appContext
    }
}