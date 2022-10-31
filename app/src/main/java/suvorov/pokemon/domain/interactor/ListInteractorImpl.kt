package suvorov.pokemon.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import suvorov.pokemon.domain.repository.ListRepository

class ListInteractorImpl(private val repository: ListRepository): ListInteractor {

    override val pokemonListFromDatabase = repository.pokemonListFromDatabase

    override suspend fun getPokemonListFromApi() {
        withContext(Dispatchers.IO) {
            repository.getPokemonListFromApi()
        }
    }

    override suspend fun updateFavoriteStatus(id: String) {
        withContext(Dispatchers.IO) {
            repository.updateFavoriteStatus(id)
        }
    }
}