package suvorov.pokemon.data.repository.favorites

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface FavoritesLocalDataSource {

    val favoritesList: LiveData<List<PokemonEntity>>

    suspend fun updateFavoriteStatus(id: String): PokemonEntity?
}