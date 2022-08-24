package ca.bell.guidomia.di

import android.content.Context
import ca.bell.guidomia.data.local.CarDao
import ca.bell.guidomia.data.local.db.AppDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    fun providesCarDao(db: AppDatabase): CarDao {
        return db.carDao()
    }

    @Provides
    @Singleton
    fun providesDataBase(appContext: Context): AppDatabase {
        return AppDatabase(appContext)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }
}