package ca.bell.guidomia.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import ca.bell.guidomia.MainApp
import ca.bell.guidomia.ui.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance appContext: Context
        ): AppComponent
    }

    val mainViewModel: MainViewModel
}


val AppCompatActivity.injector: AppComponent
    get() = (application as MainApp).appComponent