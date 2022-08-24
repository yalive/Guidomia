package ca.bell.guidomia.di

import android.content.Context
import ca.bell.guidomia.data.local.CarLocalDataSource
import ca.bell.guidomia.data.remote.CarRemoteDataSource
import ca.bell.guidomia.data.repository.GuidomiaRepository
import ca.bell.guidomia.ui.MainViewModel
import com.google.gson.Gson


object ManualDI {

    lateinit var appContext: Context

    val gson by lazy { Gson() }

    val repository by lazy {
        val localDataSource = CarLocalDataSource()
        val remoteDataSource = CarRemoteDataSource(gson, appContext)
        GuidomiaRepository(localDataSource, remoteDataSource)
    }

    val mainViewModelFactory: MainViewModel.Factory
        get() = MainViewModel.Factory(repository)


    fun initIfNecessary(appContext: Context) {
        if (this::appContext.isInitialized) return
        this.appContext = appContext
    }
}