package com.mishbanya.effectivemobiletest.presentation.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mishbanya.effectivemobiletest.R
import com.mishbanya.effectivemobiletest.databinding.FragmentFavoritesBinding
import com.mishbanya.effectivemobiletest.domain.main.usecase.FragmentChangeListener
import com.mishbanya.effectivemobiletest.domain.vacancies.usecases.IOnVacancyClickListener
import com.mishbanya.effectivemobiletest.presentation.adapters.VacanciesAdapter
import com.mishbanya.effectivemobiletest.presentation.viewmodels.FavoritesViewModel
import com.mishbanya.effectivemobiletest.presentation.viewmodels.MoreVacanciesViewModel
import com.mishbanya.effectivemobiletest.presentation.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), IOnVacancyClickListener {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private lateinit var favoritesViewModel: FavoritesViewModel

    private lateinit var favoriteVacanciesListener: FragmentChangeListener
    private lateinit var favoriteVacanciesRecyclerView: RecyclerView
    private lateinit var favoriteVacanciesAdapter: VacanciesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        favoriteVacanciesListener = context as FragmentChangeListener
    }
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

        initRecyclerView()
        initFavoritesViewModel()
        initObserversFavoritesViewModel()

        loadVacancies()
    }

    override fun onVacancyClick(position: Int) {
        favoriteVacanciesListener.onVacancyClicked()
    }

    override fun onIsFavoriteClick(position: Int) {
        favoritesViewModel.changeFavoriteness(position)
    }
    private fun initRecyclerView(){
        favoriteVacanciesRecyclerView = binding.favoriteVacanciesRv
        favoriteVacanciesRecyclerView.layoutManager = LinearLayoutManager(context)
        favoriteVacanciesAdapter = VacanciesAdapter(requireContext(),this)
        favoriteVacanciesRecyclerView.adapter = favoriteVacanciesAdapter
    }
    private fun initFavoritesViewModel() {
        Log.d("Hilt", "Creating MoreVacanciesModel client instance")
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
    }
    private fun initObserversFavoritesViewModel() {
        favoritesViewModel.favoriteVacancies.observe(viewLifecycleOwner){ data->

            if (data != null) {
                if (data.isEmpty()){
                    activity?.let { showToast(it.getString(R.string.no_vacancies)) }
                }
                favoriteVacanciesAdapter.reload(data)
                binding.favoriteVacanciesCount.text = "${favoriteVacanciesAdapter.itemCount} вакансий"
            }
            else{
                showError()
            }
        }
    }
    private fun loadVacancies(){
        favoritesViewModel.getVacancies()
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showError(){
        activity?.let {
            showToast(it.getString(R.string.error))
        }
    }

}