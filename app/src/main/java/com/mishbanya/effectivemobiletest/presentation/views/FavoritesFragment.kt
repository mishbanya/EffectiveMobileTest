package com.mishbanya.effectivemobiletest.presentation.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mishbanya.effectivemobiletest.R
import com.mishbanya.effectivemobiletest.databinding.FragmentFavoritesBinding
import com.mishbanya.effectivemobiletest.presentation.viewmodels.FavoritesViewModel
import com.mishbanya.effectivemobiletest.presentation.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private lateinit var favoritesViewModel: FavoritesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Hilt", "Creating favoritesViewModel client instance")
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        //initObserversFavoritesViewModel()
    }
}