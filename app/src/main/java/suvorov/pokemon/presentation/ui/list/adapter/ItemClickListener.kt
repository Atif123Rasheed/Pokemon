package suvorov.pokemon.presentation.ui.list.adapter

import suvorov.pokemon.data.database.PokemonEntity

interface ItemClickListener {
    fun onItemClick(model: PokemonEntity)
}