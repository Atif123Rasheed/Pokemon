package suvorov.pokemon.presentation.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.FragmentFavoritesBinding
import suvorov.pokemon.presentation.ui.list.adapter.ItemClickListener
import suvorov.pokemon.presentation.ui.base.BaseFragment
import suvorov.pokemon.presentation.ui.list.adapter.FavoriteClickListener
import suvorov.pokemon.presentation.ui.list.adapter.ListAdapter
import suvorov.pokemon.utils.Constants.POKEMON_ID
import suvorov.pokemon.utils.Constants.POKEMON_NAME
import suvorov.pokemon.utils.doOnChange

class FavoritesFragment: BaseFragment<FragmentFavoritesBinding>(
    FragmentFavoritesBinding::inflate), ItemClickListener, FavoriteClickListener {

    private val viewModel: FavoritesViewModel by viewModel()
    private var favoritesAdapter = ListAdapter(this,this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initializeView()
    }

    private fun observeViewModel() {
        viewModel.favoritesList.doOnChange(this) {
            favoritesAdapter.updateList(it)

            binding.favoritesTextView.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun initializeView() {
        activity?.window?.statusBarColor = requireContext().getColor(R.color.green)

        val pokemonLayoutManager = GridLayoutManager(context, 3)
        binding.favoritesRecyclerView.apply {
            layoutManager = pokemonLayoutManager
            adapter = favoritesAdapter
        }

        binding.toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onItemClick(model: PokemonEntity) {
        val bundle = bundleOf(POKEMON_ID to model.id, POKEMON_NAME to model.name)
        findNavController().navigate(R.id.action_favoritesFragment_to_pagesFragment, bundle)
    }

    override fun onFavoriteClick(id: String) {
        viewModel.updateFavoriteStatus(id)
    }
}