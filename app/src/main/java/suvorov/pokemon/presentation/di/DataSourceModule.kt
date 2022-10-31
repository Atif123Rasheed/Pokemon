package suvorov.pokemon.presentation.di

import org.koin.dsl.module
import suvorov.pokemon.data.repository.favorites.FavoritesLocalDataSource
import suvorov.pokemon.data.repository.favorites.FavoritesLocalDataSourceImpl
import suvorov.pokemon.data.repository.list.ListLocalDataSource
import suvorov.pokemon.data.repository.list.ListLocalDataSourceImpl
import suvorov.pokemon.data.repository.list.ListRemoteDataSource
import suvorov.pokemon.data.repository.list.ListRemoteDataSourceImpl
import suvorov.pokemon.data.repository.page.PageLocalDataSource
import suvorov.pokemon.data.repository.page.PageLocalDataSourceImpl

val dataSourceModule = module {
    factory<ListLocalDataSource> {
        ListLocalDataSourceImpl(get())
    }

    factory<ListRemoteDataSource> {
        ListRemoteDataSourceImpl(get())
    }

    factory<FavoritesLocalDataSource> {
        FavoritesLocalDataSourceImpl(get())
    }

    factory<PageLocalDataSource> {
        PageLocalDataSourceImpl(get())
    }
}