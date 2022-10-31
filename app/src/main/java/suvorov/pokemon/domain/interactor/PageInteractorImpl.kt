package suvorov.pokemon.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import suvorov.pokemon.domain.repository.PageRepository

class PageInteractorImpl(private val repository: PageRepository): PageInteractor {

    override fun getPokemonById(id: String) = repository.getPokemonById(id)

    override fun getEvolutionsByIds(ids: List<String>) = repository.getEvolutionsByIds(ids)

    override suspend fun updateFavoriteStatus(id: String) {
        withContext(Dispatchers.IO) {
            repository.updateFavoriteStatus(id)
        }
    }
}