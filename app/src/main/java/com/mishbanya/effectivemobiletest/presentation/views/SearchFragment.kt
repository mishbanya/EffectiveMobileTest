package com.mishbanya.effectivemobiletest.presentation.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mishbanya.effectivemobiletest.R
import com.mishbanya.effectivemobiletest.databinding.FragmentSearchBinding
import com.mishbanya.effectivemobiletest.domain.main.usecase.FragmentChangeListener
import com.mishbanya.effectivemobiletest.domain.offers.usecases.IOnOfferClickListener
import com.mishbanya.effectivemobiletest.domain.vacancies.usecases.IOnVacancyClickListener
import com.mishbanya.effectivemobiletest.presentation.adapters.OffersAdapter
import com.mishbanya.effectivemobiletest.presentation.adapters.VacanciesAdapter
import com.mishbanya.effectivemobiletest.presentation.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), IOnOfferClickListener, IOnVacancyClickListener {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private lateinit var searchViewModel: SearchViewModel

    private lateinit var searchListener: FragmentChangeListener

    private lateinit var offersRecyclerView: RecyclerView
    private lateinit var vacanciesRecyclerView: RecyclerView
    private lateinit var offersAdapter: OffersAdapter
    private lateinit var vacanciesAdapter: VacanciesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchListener = context as FragmentChangeListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSearchViewModel()
        initObserversSearchViewModel()
        initListenerMoreButton()

        loadOffersAndVacancies()
    }
    private fun loadOffersAndVacancies(){
        searchViewModel.getOffersAndVacancies()
    }
    private fun initListenerMoreButton(){
        binding.moreVacanciesButton.setOnClickListener {
            searchListener.onMoreClicked()
        }
    }
    override fun onOfferClick(position: Int) {
        searchViewModel.offerClick(requireContext(), position)
    }

    override fun onVacancyClick(position: Int) {
        searchListener.onVacancyClicked()
    }

    override fun onIsFavoriteClick(position: Int) {
        TODO("Not yet implemented")
    }
    private fun initRecyclerView(){
        offersRecyclerView = binding.offersRv
        offersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        offersAdapter = OffersAdapter(requireContext(),this)
        offersRecyclerView.adapter = offersAdapter

        vacanciesRecyclerView = binding.vacanciesRv
        vacanciesRecyclerView.layoutManager = LinearLayoutManager(context)
        vacanciesAdapter = VacanciesAdapter(requireContext(),this)
        vacanciesRecyclerView.adapter = vacanciesAdapter
    }
    private fun initSearchViewModel() {
        Log.d("Hilt", "Creating SearchViewModel client instance")
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }
    private fun initObserversSearchViewModel() {
        searchViewModel.offers.observe(viewLifecycleOwner){ data->

            if (data != null) {
                if (data.isEmpty()){
                    activity?.let { showToast(it.getString(R.string.no_offers)) }
                }
                offersAdapter.reload(data)
            }
            else{
                showError()
            }
        }
        searchViewModel.vacancies.observe(viewLifecycleOwner){ data->

            if (data != null) {
                if (data.isEmpty()){
                    activity?.let { showToast(it.getString(R.string.no_vacancies)) }
                }
                vacanciesAdapter.reload(data.take(3))
                binding.moreVacanciesButton.text = "Показть все ${data.count()} вакансий"
            }
            else{
                showError()
            }
        }
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