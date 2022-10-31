package suvorov.pokemon.data.repository.page

import suvorov.pokemon.data.database.PokemonDao
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.data.mapper.EntityMapper.toEntity

class PageLocalDataSourceImpl(private val dao: PokemonDao): PageLocalDataSource {

    override fun getPokemonById(id: String) = dao.getPokemonById(id)

    override fun getEvolutionsByIds(ids: List<String>) = dao.getEvolutionsByIds(ids)

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