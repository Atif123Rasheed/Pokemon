package suvorov.pokemon.presentation.ui.evolution.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.ItemEvolutionBinding
import suvorov.pokemon.presentation.ui.list.adapter.FavoriteClickListener
import suvorov.pokemon.presentation.ui.list.adapter.ItemClickListener

class EvolutionAdapter(
    private val itemClickListener: ItemClickListener,
    private val favoriteClickListener: FavoriteClickListener
): RecyclerView.Adapter<EvolutionViewHolder>() {

    private val pokemonList = arrayListOf<PokemonEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        return EvolutionViewHolder(
            ItemEvolutionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        holder.bind(pokemonList[position], itemClickListener, favoriteClickListener)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<PokemonEntity>) {
        pokemonList.clear()
        pokemonList.addAll(list)
        notifyDataSetChanged()
    }


}