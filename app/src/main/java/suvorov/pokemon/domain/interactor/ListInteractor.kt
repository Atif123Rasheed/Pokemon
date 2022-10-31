package suvorov.pokemon.domain.interactor

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface ListInteractor {

    val pokemonListFromDatabase: LiveData<List<PokemonEntity>>

    suspend fun getPokemonListFromApi()

    suspend fun updateFavoriteStatus(id: String)
}