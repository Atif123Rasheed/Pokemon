package suvorov.pokemon.data.repository.page

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface PageLocalDataSource {

    fun getPokemonById(id: String): LiveData<PokemonEntity>

    fun getEvolutionsByIds(ids: List<String>): LiveData<List<PokemonEntity>>

    suspend fun updateFavoriteStatus(id: String): PokemonEntity?
}