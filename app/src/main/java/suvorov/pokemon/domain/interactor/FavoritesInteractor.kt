package suvorov.pokemon.domain.interactor

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface FavoritesInteractor {

    val favoritesList: LiveData<List<PokemonEntity>>

    suspend fun updateFavoriteStatus(id: String)
}