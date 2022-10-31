package suvorov.pokemon.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import suvorov.pokemon.presentation.ui.favorites.FavoritesViewModel
import suvorov.pokemon.presentation.ui.list.ListViewModel
import suvorov.pokemon.presentation.ui.pages.PagesViewModel

val viewModelModule = module {
    viewModel {
        ListViewModel(get())
    }

    viewModel {
        FavoritesViewModel(get())
    }

    viewModel {
        PagesViewModel(get())
    }
}