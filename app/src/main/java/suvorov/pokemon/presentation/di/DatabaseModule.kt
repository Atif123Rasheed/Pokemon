package suvorov.pokemon.presentation.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonDatabase

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            PokemonDatabase::class.java,
            androidApplication().applicationContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<PokemonDatabase>().pokemonDao()
    }
}