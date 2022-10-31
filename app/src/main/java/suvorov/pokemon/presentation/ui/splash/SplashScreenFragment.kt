package suvorov.pokemon.presentation.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import suvorov.pokemon.R
import suvorov.pokemon.databinding.FragmentSplashBinding
import suvorov.pokemon.presentation.ui.base.BaseFragment

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment: BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = requireContext().getColor(R.color.green)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_listFragment)
        }, 2000)
    }
}