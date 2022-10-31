package suvorov.pokemon.presentation.di

import org.koin.dsl.module
import suvorov.pokemon.domain.interactor.*

val interactorModule = module {
    factory<ListInteractor> {
        ListInteractorImpl(get())
    }

    factory<FavoritesInteractor> {
        FavoritesInteractorImpl(get())
    }

    factory<PageInteractor> {
        PageInteractorImpl(get())
    }
}