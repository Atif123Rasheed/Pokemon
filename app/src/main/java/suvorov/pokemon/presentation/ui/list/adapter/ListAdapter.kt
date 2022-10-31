package suvorov.pokemon.presentation.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.ItemListBinding
import java.util.*

class ListAdapter(
    private val itemClickListener: ItemClickListener,
    private val favoriteClickListener: FavoriteClickListener
): RecyclerView.Adapter<ListViewHolder>() {

    private val pokemonList = arrayListOf<PokemonEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(
                    parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
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

    @SuppressLint("NotifyDataSetChanged")
    fun filterPokemon(list: List<PokemonEntity>, query: String) {
        pokemonList.clear()
        for (pokemon in list) {
            if (pokemon.name?.lowercase(Locale.ROOT)?.contains(query.lowercase(Locale.ROOT)) == true) {
                pokemonList.add(pokemon)
            }
        }
        notifyDataSetChanged()
    }
}