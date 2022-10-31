package suvorov.pokemon.presentation.ui.stats

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import suvorov.pokemon.databinding.FragmentStatsBinding
import suvorov.pokemon.presentation.ui.base.BaseFragment
import suvorov.pokemon.presentation.ui.pages.PagesViewModel
import suvorov.pokemon.utils.Constants
import suvorov.pokemon.utils.PokemonColor
import suvorov.pokemon.utils.doOnChange
import suvorov.pokemon.utils.intEmptyIfNull

class StatsFragment: BaseFragment<FragmentStatsBinding>(FragmentStatsBinding::inflate) {

    private val statesViewModel: PagesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        val id = checkNotNull(arguments?.getString(Constants.POKEMON_ID))

        statesViewModel.getPokemonById(id).doOnChange(this) { pokemon ->
            binding.apply {
                hpTextView.text = pokemon.hp.toString()
                attackTextView.text = pokemon.attack.toString()
                defenseTextView.text = pokemon.defense.toString()
                specialAttackTextView.text = pokemon.specialAttack.toString()
                specialDefenseTextView.text = pokemon.specialDefense.toString()
                speedTextView.text = pokemon.speed.toString()

                hpProgressbar.progress = pokemon.hp.intEmptyIfNull()
                attackProgressbar.progress = pokemon.attack.intEmptyIfNull()
                defenseProgressbar.progress = pokemon.defense.intEmptyIfNull()
                specialAttackProgressbar.progress = pokemon.specialAttack.intEmptyIfNull()
                specialDefenseProgressbar.progress = pokemon.specialDefense.intEmptyIfNull()
                speedProgressbar.progress = pokemon.speed.intEmptyIfNull()

                hpProgressbar.progressDrawable
                    .setTint(
                        PokemonColor(requireView().context)
                            .getPokemonColor(pokemon.typeOfPokemon))

                attackProgressbar.progressDrawable
                    .setTint(
                        PokemonColor(requireView().context)
                            .getPokemonColor(pokemon.typeOfPokemon))

                defenseProgressbar.progressDrawable
                    .setTint(
                        PokemonColor(requireView().context)
                            .getPokemonColor(pokemon.typeOfPokemon))

                specialAttackProgressbar.progressDrawable
                    .setTint(
                        PokemonColor(requireView().context)
                            .getPokemonColor(pokemon.typeOfPokemon))

                specialDefenseProgressbar.progressDrawable
                    .setTint(
                        PokemonColor(requireView().context)
                            .getPokemonColor(pokemon.typeOfPokemon))

                speedProgressbar.progressDrawable
                    .setTint(
                        PokemonColor(requireView().context)
                            .getPokemonColor(pokemon.typeOfPokemon))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = StatsFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.POKEMON_ID, id)
            }
        }
    }
}