package suvorov.pokemon.data.repository.favorites

import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.domain.repository.FavoritesRepository
import suvorov.pokemon.utils.Constants

class FavoritesRepositoryImpl(
    private val localDataSource: FavoritesLocalDataSource
    ): FavoritesRepository {

    override val favoritesList = localDataSource.favoritesList

    override suspend fun updateFavoriteStatus(id: String): Result<PokemonEntity> {
        val result = localDataSource.updateFavoriteStatus(id)
        return result?.let {
            Result.Success(it)
        } ?: Result.Error(Constants.UNEXPECTED_ERROR)
    }
}