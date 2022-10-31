package suvorov.pokemon.domain.repository

import androidx.lifecycle.LiveData
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.data.database.PokemonEntity

interface FavoritesRepository {

    val favoritesList: LiveData<List<PokemonEntity>>

    suspend fun updateFavoriteStatus(id: String): Result<PokemonEntity>
}