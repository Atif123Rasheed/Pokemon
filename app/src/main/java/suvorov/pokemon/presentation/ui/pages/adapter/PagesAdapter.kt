package suvorov.pokemon.presentation.ui.pages.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import suvorov.pokemon.presentation.ui.evolution.EvolutionFragment
import suvorov.pokemon.presentation.ui.intro.IntroFragment
import suvorov.pokemon.presentation.ui.stats.StatsFragment

class PagesAdapter(fragment: Fragment, private val pokemonId: String): FragmentStateAdapter(fragment) {

    data class Page(val fragment: () -> Fragment)

    private val pages = listOf(
        Page { IntroFragment.newInstance(pokemonId) },

        Page { StatsFragment.newInstance(pokemonId) },

        Page { EvolutionFragment.newInstance(pokemonId) },
    )

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position].fragment()
    }
}