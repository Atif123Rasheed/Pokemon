package suvorov.pokemon.presentation.ui.evolution.adapter

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.ItemEvolutionBinding
import suvorov.pokemon.presentation.ui.list.adapter.FavoriteClickListener
import suvorov.pokemon.presentation.ui.list.adapter.ItemClickListener
import suvorov.pokemon.utils.PokemonColor
import suvorov.pokemon.utils.ShowHelper
import suvorov.pokemon.utils.loadImage

class EvolutionViewHolder(
    private val binding: ItemEvolutionBinding
): RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(
        model: PokemonEntity,
        itemClickListener: ItemClickListener,
        favoriteClickListener: FavoriteClickListener
    ) {

        binding.apply {
            idTextView.text = model.id
            nameTextView.text = model.name
            categoryTextView.text = model.category
            imageView.loadImage(model.imageUrl)
        }

        binding.root.setOnClickListener {
            itemClickListener.onItemClick(model)
        }

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

        model.typeOfPokemon?.apply {
            getOrNull(0).let { firstType ->
                binding.thirdTypeTextView.text = firstType
                binding.thirdTypeTextView.isVisible = firstType != null
            }

            getOrNull(1).let { secondType ->
                binding.secondTypeTextView.text = secondType
                binding.secondTypeTextView.isVisible = secondType != null
            }

            getOrNull(2).let { thirdType ->
                binding.firstTypeTextView.text = thirdType
                binding.firstTypeTextView.isVisible = thirdType != null
            }
        }

        val color = PokemonColor(context).getPokemonColor(model.typeOfPokemon)

        binding.pokemonCardView.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}