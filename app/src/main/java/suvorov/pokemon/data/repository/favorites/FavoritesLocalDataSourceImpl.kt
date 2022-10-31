package suvorov.pokemon.data.repository.favorites

import suvorov.pokemon.data.database.PokemonDao
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.data.mapper.EntityMapper.toEntity

class FavoritesLocalDataSourceImpl(private val dao: PokemonDao): FavoritesLocalDataSource {

    override val favoritesList = dao.getFavoritesList()

    override suspend fun updateFavoriteStatus(id: String): PokemonEntity? {
        val pokemon = dao.getPokemonFromId(id)
        pokemon?.let {
            val pokemonEntity = toEntity(it)
            if (dao.update(pokemonEntity) > 0) {
                return pokemonEntity
            }
        }
        return null
    }
}