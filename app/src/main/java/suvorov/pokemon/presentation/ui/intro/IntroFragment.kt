package suvorov.pokemon.presentation.ui.intro

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.databinding.FragmentIntroBinding
import suvorov.pokemon.presentation.ui.base.BaseFragment
import suvorov.pokemon.presentation.ui.pages.PagesViewModel
import suvorov.pokemon.utils.Constants.POKEMON_ID
import suvorov.pokemon.utils.doOnChange

class IntroFragment: BaseFragment<FragmentIntroBinding>(FragmentIntroBinding::inflate) {

    private val viewModel: PagesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        val id = checkNotNull(arguments?.getString(POKEMON_ID))

        viewModel.getPokemonById(id).doOnChange(this) {
            binding.apply {
                heightTextView.text = it.height
                weightTextView.text = it.weight
                maleTextView.text = it.malePercentage
                femaleTextView.text = it.femalePercentage
                xdescriptionTextView.text = it.xDescription
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = IntroFragment().apply {
            arguments = Bundle().apply {
                putString(POKEMON_ID, id)
            }
        }
    }
}