package suvorov.pokemon.presentation.ui.list.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.recyclerview.widget.RecyclerView
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.ItemListBinding
import suvorov.pokemon.presentation.ui.list.adapter.FavoriteClickListener
import suvorov.pokemon.presentation.ui.list.adapter.ItemClickListener
import suvorov.pokemon.utils.PokemonColor
import suvorov.pokemon.utils.ShowHelper
import suvorov.pokemon.utils.loadImage

class ListViewHolder(
    private val binding: ItemListBinding
): RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(
        model: PokemonEntity,
        itemClickListener: ItemClickListener,
        favoriteClickListener: FavoriteClickListener
    ) {

        binding.idTextView.text = model.id
        binding.nameTextView.text = model.name
        binding.imageView.loadImage(model.imageUrl)

        val color = PokemonColor(context).getPokemonColor(model.typeOfPokemon)

        binding.pokemonCardView.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        binding.favoriteImageView.setImageResource(
            if (model.isFavorite) R.drawable.ic_baseline_donut_small_24
            else R.drawable.ic_baseline_donut_large_24
        )

        binding.favoriteImageView.setOnClickListener {
            favoriteClickListener.onFavoriteClick(model.id)

            ShowHelper.showToast(
                context,
                if (model.isFavorite) "${model.name} ${context.getString(R.string.removed_from_favorites)}"
                else "${model.name} ${context.getString(R.string.added_to_favorites)}"
            )
        }

        binding.root.setOnClickListener {
            itemClickListener.onItemClick(model)
        }
    }
}