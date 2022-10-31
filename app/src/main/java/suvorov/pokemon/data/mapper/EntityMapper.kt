package suvorov.pokemon.data.mapper

import suvorov.pokemon.data.database.PokemonEntity

object EntityMapper {
    fun toEntity(type: PokemonEntity): PokemonEntity {
        return PokemonEntity(
            type.id,
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
            type.isFavorite.not()
        )
    }
}