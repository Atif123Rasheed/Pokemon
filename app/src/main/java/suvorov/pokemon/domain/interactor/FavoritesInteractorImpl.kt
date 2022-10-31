package suvorov.pokemon.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import suvorov.pokemon.domain.repository.FavoritesRepository

class FavoritesInteractorImpl(private val repository: FavoritesRepository): FavoritesInteractor {

    override val favoritesList = repository.favoritesList

    override suspend fun updateFavoriteStatus(id: String) {
        withContext(Dispatchers.IO) {
            repository.updateFavoriteStatus(id)
        }
    }
}