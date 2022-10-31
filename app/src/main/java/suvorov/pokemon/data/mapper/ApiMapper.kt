package suvorov.pokemon.data.mapper

import suvorov.pokemon.data.api.PokemonResponse
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.utils.stringEmptyIfNull

object ApiMapper {
    fun toEntity(type: PokemonResponse, list: List<String>): PokemonEntity {
        return PokemonEntity(
            type.id.stringEmptyIfNull(),
            type.name,
            type.imageUrl,
            type.xDescription,
            type.height,
            type.category,
            type.weight,
            type.typeOfPokemon,
            type.evolutions,
            type.hp,
            type.attack,
            type.defense,
            type.specialAttack,
            type.specialDefense,
            type.speed,
            type.malePercentage,
            type.femalePercentage,
            list.contains(type.id)
        )
    }
}