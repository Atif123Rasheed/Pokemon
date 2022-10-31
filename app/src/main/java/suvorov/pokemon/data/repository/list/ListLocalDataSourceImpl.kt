package suvorov.pokemon.data.repository.list

import suvorov.pokemon.data.database.PokemonDao
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.data.mapper.EntityMapper.toEntity

class ListLocalDataSourceImpl(private val dao: PokemonDao): ListLocalDataSource {

    override val pokemonList = dao.getPokemonList()

    override suspend fun insert(list: List<PokemonEntity>) {
        if (list.isNotEmpty()) {
            dao.insert(list)
        }
    }

    override suspend fun favoriteIds() = dao.favoriteIds()

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