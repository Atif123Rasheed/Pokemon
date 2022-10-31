package suvorov.pokemon.data.repository.list

import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.data.mapper.ApiMapper.toEntity
import suvorov.pokemon.domain.repository.ListRepository
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.domain.common.succeed
import suvorov.pokemon.utils.Constants.UNEXPECTED_ERROR

class ListRepositoryImpl(
    private val localDataSource: ListLocalDataSource,
    private val remoteDataSource: ListRemoteDataSource
    ): ListRepository {

    override val pokemonListFromDatabase = localDataSource.pokemonList

    override suspend fun getPokemonListFromApi() {
        when(val result = remoteDataSource.getPokemonList()) {
            is Result.Success -> {
                if(result.succeed) {
                    val favoriteIds = localDataSource.favoriteIds()
                    val customList = result.data.let { list ->
                        list.filter { item -> item.id.isNullOrEmpty().not() }
                        list.map { toEntity(it, favoriteIds) }
                    }
                    localDataSource.insert(customList)
                    Result.Success(true)
                } else {
                    Result.Error(UNEXPECTED_ERROR)
                }
            }
            else -> result as Result.Error
        }
    }

    override suspend fun updateFavoriteStatus(id: String): Result<PokemonEntity> {
        val result = localDataSource.updateFavoriteStatus(id)
        return result?.let {
            Result.Success(it)
        } ?: Result.Error(UNEXPECTED_ERROR)
    }
}