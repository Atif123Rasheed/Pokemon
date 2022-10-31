package suvorov.pokemon.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import suvorov.pokemon.domain.interactor.FavoritesInteractor

class FavoritesViewModel(private val interactor: FavoritesInteractor): ViewModel() {

    val favoritesList = interactor.favoritesList

    fun updateFavoriteStatus(id: String) {
        viewModelScope.launch {
            interactor.updateFavoriteStatus(id)
        }
    }
}