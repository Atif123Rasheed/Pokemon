package suvorov.pokemon.presentation.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import suvorov.pokemon.domain.interactor.PageInteractor

class PagesViewModel(private val interactor: PageInteractor): ViewModel() {

    fun getPokemonById(id: String) = interactor.getPokemonById(id)

    fun getEvolutionsByIds(ids: List<String>) = interactor.getEvolutionsByIds(ids)

    fun updateFavoriteStatus(id: String) {
        viewModelScope.launch {
            interactor.updateFavoriteStatus(id)
        }
    }
}