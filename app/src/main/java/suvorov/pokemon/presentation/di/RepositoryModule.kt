package suvorov.pokemon.presentation.di

import org.koin.dsl.module
import suvorov.pokemon.data.repository.favorites.FavoritesRepositoryImpl
import suvorov.pokemon.data.repository.list.ListRepositoryImpl
import suvorov.pokemon.data.repository.page.PageRepositoryImpl
import suvorov.pokemon.domain.repository.FavoritesRepository
import suvorov.pokemon.domain.repository.ListRepository
import suvorov.pokemon.domain.repository.PageRepository

val repositoryModule = module {
    factory<ListRepository> {
        ListRepositoryImpl(get(), get())
    }

    factory<FavoritesRepository> {
        FavoritesRepositoryImpl(get())
    }

    factory<PageRepository> {
        PageRepositoryImpl(get())
    }
}