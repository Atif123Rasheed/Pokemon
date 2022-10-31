package suvorov.pokemon.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import suvorov.pokemon.utils.ListStringConverter

@Entity(tableName = "pokemon")
@TypeConverters(ListStringConverter::class)
data class PokemonEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val imageUrl: String?,
    val xDescription: String?,
    val height: String?,
    val category: String?,
    val weight: String?,
    val typeOfPokemon: List<String>?,
    val evolutions: List<String>?,
    val hp: Int?,
    val attack: Int?,
    val defense: Int?,
    val specialAttack: Int?,
    val specialDefense: Int?,
    val speed: Int?,
    val malePercentage: String?,
    val femalePercentage: String?,
    val isFavorite: Boolean = false
)