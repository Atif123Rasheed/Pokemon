package suvorov.pokemon.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.R
import suvorov.pokemon.databinding.FragmentPagesBinding
import suvorov.pokemon.presentation.ui.base.BaseFragment
import suvorov.pokemon.presentation.ui.pages.adapter.PagesAdapter
import suvorov.pokemon.utils.*
import suvorov.pokemon.utils.Constants.POKEMON_ID

class PagesFragment: BaseFragment<FragmentPagesBinding>(FragmentPagesBinding::inflate) {

    private val viewModel: PagesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString(POKEMON_ID))
        observeViewModel(id)
        initializeView(id)
    }

    private fun observeViewModel(id: String) {
        viewModel.getPokemonById(id).doOnChange(this) { pokemon ->
            binding.toolbar.setBackgroundColor(
                PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon))

            activity?.window?.statusBarColor =
                PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon)

            binding.tabLayout.apply {
                setTabTextColors(
                    R.color.black,
                    PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon))

                setSelectedTabIndicatorColor(
                    PokemonColor(requireActivity()).getPokemonColor(pokemon.typeOfPokemon))
            }

            binding.apply {
                idTextView.text = pokemon.id
                nameTextView.text = pokemon.name
                categoryTextView.text = pokemon.category
                imageView.loadImage(pokemon.imageUrl)

                favoriteImageView.setImageResource(
                    if (pokemon.isFavorite) R.drawable.ic_baseline_donut_small_24
                    else R.drawable.ic_baseline_donut_large_24
                )

                favoriteImageView.setOnClickListener {
                    viewModel.updateFavoriteStatus(pokemon.id)

                    ShowHelper.showToast(
                        requireContext(),
                        if (pokemon.isFavorite) "${pokemon.name} ${getString(R.string.removed_from_favorites)}"
                        else "${pokemon.name} ${getString(R.string.added_to_favorites)}"
                    )
                }
            }

            pokemon.typeOfPokemon?.apply {
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
        }
    }

    private fun initializeView(id: String) {
        binding.viewPager.adapter = PagesAdapter(this, id)

        binding.tabLayout.setupWithViewPager(binding.viewPager, listOf(
            getString(R.string.name_first_table_item),
            getString(R.string.name_second_table_item),
            getString(R.string.name_third_table_item))
        )

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}