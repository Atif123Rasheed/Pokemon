package suvorov.pokemon.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import suvorov.pokemon.presentation.di.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                databaseModule,
                dataSourceModule,
                repositoryModule,
                interactorModule,
                viewModelModule
            )
        }
    }
}