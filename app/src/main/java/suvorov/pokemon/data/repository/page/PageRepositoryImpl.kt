package suvorov.pokemon.data.repository.page

import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.domain.repository.PageRepository
import suvorov.pokemon.utils.Constants.UNEXPECTED_ERROR

class PageRepositoryImpl(private val localDataSource: PageLocalDataSource): PageRepository {

    override fun getPokemonById(id: String) = localDataSource.getPokemonById(id)

    override fun getEvolutionsByIds(ids: List<String>) = localDataSource.getEvolutionsByIds(ids)

    override suspend fun updateFavoriteStatus(id: String): Result<PokemonEntity> {
        val result = localDataSource.updateFavoriteStatus(id)
        return result?.let {
            Result.Success(it)
        } ?: Result.Error(UNEXPECTED_ERROR)
    }
}