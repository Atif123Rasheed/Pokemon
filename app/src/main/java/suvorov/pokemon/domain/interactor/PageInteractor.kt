package suvorov.pokemon.domain.interactor

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface PageInteractor {

    fun getPokemonById(id: String): LiveData<PokemonEntity>

    fun getEvolutionsByIds(ids: List<String>): LiveData<List<PokemonEntity>>

    suspend fun updateFavoriteStatus(id: String)
}