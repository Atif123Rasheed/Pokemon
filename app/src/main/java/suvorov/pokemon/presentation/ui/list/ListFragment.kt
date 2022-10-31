package suvorov.pokemon.presentation.ui.list

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.FragmentListBinding
import suvorov.pokemon.presentation.ui.list.adapter.ItemClickListener
import suvorov.pokemon.presentation.ui.base.BaseFragment
import suvorov.pokemon.presentation.ui.list.adapter.FavoriteClickListener
import suvorov.pokemon.presentation.ui.list.adapter.ListAdapter
import suvorov.pokemon.utils.*
import suvorov.pokemon.utils.Constants.POKEMON_ID

class ListFragment:
    BaseFragment<FragmentListBinding>(FragmentListBinding::inflate),
    ItemClickListener,
    FavoriteClickListener,
    SearchView.OnQueryTextListener {

    private val viewModel: ListViewModel by viewModel()
    private val listAdapter = ListAdapter(this, this)

    private var pokemonList = listOf<PokemonEntity>()
    private var searchRequest = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initializeView()
    }

    private fun observeViewModel() {
        viewModel.isLoading.doOnChange(this) {
            binding.progressBar.visibility =
                if(viewModel.isListEmpty() && it) View.VISIBLE else View.GONE
        }

        viewModel.pokemonListFromDatabase.doOnChange(this) {
            listAdapter.filterPokemon(it, searchRequest)
            pokemonList = it

            binding.errorTextView.visibility =
                if(viewModel.isListEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun initializeView() {
        viewModel.getPokemonFromApi()

        binding.pokemonSearchView.setOnQueryTextListener(this)

        activity?.window?.statusBarColor = requireContext().getColor(R.color.green)

        val pokemonLayoutManager = GridLayoutManager(context, 3)
        binding.pokemonListRecyclerView.apply {
            layoutManager = pokemonLayoutManager
            adapter = listAdapter
        }

        binding.pokemonSearchView.apply {
            findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
                .setHintTextColor(requireContext().getColor(R.color.partially_white))
            findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
                .setTextColor(Color.WHITE)
            findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
                .setColorFilter(Color.WHITE)
        }

        binding.errorTextView.setOnClickListener {
            viewModel.getPokemonFromApi()
        }

        binding.transitionFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_favoritesFragment)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            listAdapter.filterPokemon(pokemonList, newText)
            searchRequest = newText
        }
        return true
    }

    override fun onItemClick(model: PokemonEntity) {
        val bundle = bundleOf(POKEMON_ID to model.id)
        findNavController().navigate(R.id.action_listFragment_to_pagesFragment, bundle)
    }

    override fun onFavoriteClick(id: String) {
        viewModel.updateFavoriteStatus(id)
        KeyboardHelper.hideKeyboard(requireActivity())
    }
}