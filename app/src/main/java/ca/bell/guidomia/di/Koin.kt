package ca.bell.guidomia.di

import ca.bell.guidomia.data.local.CarLocalDataSource
import ca.bell.guidomia.data.local.db.AppDatabase
import ca.bell.guidomia.data.remote.CarRemoteDataSource
import ca.bell.guidomia.data.repository.GuidomiaRepository
import ca.bell.guidomia.ui.MainViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val dataModule = module {
    single { AppDatabase(get()) }
    single { Gson() }
    single {
        val localDataSource = CarLocalDataSource(get())
        val remoteDataSource = CarRemoteDataSource(get(), get())
        GuidomiaRepository(localDataSource, remoteDataSource)
    }
    factory { get<AppDatabase>().carDao() }
}