package suvorov.pokemon.presentation.ui.evolution

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.R
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.databinding.FragmentEvolutionBinding
import suvorov.pokemon.presentation.ui.list.adapter.FavoriteClickListener
import suvorov.pokemon.presentation.ui.list.adapter.ItemClickListener
import suvorov.pokemon.presentation.ui.base.BaseFragment
import suvorov.pokemon.presentation.ui.evolution.adapter.EvolutionAdapter
import suvorov.pokemon.presentation.ui.pages.PagesViewModel
import suvorov.pokemon.utils.Constants.POKEMON_ID
import suvorov.pokemon.utils.doOnChange

class EvolutionFragment: BaseFragment<FragmentEvolutionBinding>(
    FragmentEvolutionBinding::inflate), ItemClickListener, FavoriteClickListener {

    private val viewModel: PagesViewModel by viewModel()
    private val evolutionAdapter = EvolutionAdapter(this, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initializeView()
    }

    private fun observeViewModel() {
        val id = checkNotNull(arguments?.getString(POKEMON_ID))

        viewModel.getPokemonById(id).doOnChange(this) {
            val pokemonEvolution = it.evolutions ?: emptyList()
            viewModel.getEvolutionsByIds(pokemonEvolution).doOnChange(this) { list ->
                evolutionAdapter.updateList(list)
            }
        }
    }

    private fun initializeView() {
        binding.evolutionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = evolutionAdapter
        }
    }

    override fun onItemClick(model: PokemonEntity) {
        val bundle = bundleOf(POKEMON_ID to model.id)
        findNavController().navigate(R.id.action_pagesFragment_to_listFragment, bundle)
        findNavController().navigate(R.id.action_listFragment_to_pagesFragment, bundle)
    }

    override fun onFavoriteClick(id: String) {
        viewModel.updateFavoriteStatus(id)
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putString(POKEMON_ID, id)
            }
        }
    }
}