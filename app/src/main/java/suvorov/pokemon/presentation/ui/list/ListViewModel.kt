package suvorov.pokemon.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import suvorov.pokemon.domain.interactor.ListInteractor

class ListViewModel(private val interactor: ListInteractor): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val pokemonListFromDatabase = interactor.pokemonListFromDatabase

    fun isListEmpty(): Boolean {
        return pokemonListFromDatabase.value?.isEmpty() ?: true
    }

    fun getPokemonFromApi() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            interactor.getPokemonListFromApi()
            _isLoading.postValue(false)
        }
    }

    fun updateFavoriteStatus(symbol: String) {
        viewModelScope.launch {
            interactor.updateFavoriteStatus(symbol)
        }
    }
}