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
import com.mishbanya.effectivemobiletest.databinding.FragmentMoreVacanciesBinding
import com.mishbanya.effectivemobiletest.domain.main.usecase.FragmentChangeListener
import com.mishbanya.effectivemobiletest.domain.vacancies.usecases.IOnVacancyClickListener
import com.mishbanya.effectivemobiletest.presentation.adapters.VacanciesAdapter
import com.mishbanya.effectivemobiletest.presentation.viewmodels.MoreVacanciesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoreVacanciesFragment: Fragment(), IOnVacancyClickListener {
    private val binding by viewBinding(FragmentMoreVacanciesBinding::bind)
    private lateinit var moreVacanciesListener: FragmentChangeListener
    private lateinit var vacanciesRecyclerView: RecyclerView
    @Inject
    lateinit var vacanciesAdapter: VacanciesAdapter
    private lateinit var moreVacanciesViewModel: MoreVacanciesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        moreVacanciesListener = context as FragmentChangeListener
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more_vacancies, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initMoreVacanciesViewModel()
        initObserversMoreVacanciesViewModel()
        initListenerBackButton()

        loadVacancies()
    }
    private fun loadVacancies(){
        moreVacanciesViewModel.getVacancies()
    }
    private fun initListenerBackButton(){
        binding.backButton.setOnClickListener {
            moreVacanciesListener.onSearchClicked()
        }
    }
    override fun onVacancyClick(position: Int) {
        moreVacanciesListener.onVacancyClicked()
    }

    override fun onIsFavoriteClick(position: Int) {
        moreVacanciesViewModel.changeFavoriteness(position)
    }
    private fun initRecyclerView(){
        vacanciesRecyclerView = binding.vacanciesRv
        vacanciesRecyclerView.layoutManager = LinearLayoutManager(context)
        vacanciesAdapter.setContextAndListener(requireContext(), this)
        vacanciesRecyclerView.adapter = vacanciesAdapter
    }
    private fun initMoreVacanciesViewModel() {
        Log.d("Hilt", "Creating MoreVacanciesModel client instance")
        moreVacanciesViewModel = ViewModelProvider(this)[MoreVacanciesViewModel::class.java]
    }
    private fun initObserversMoreVacanciesViewModel() {
        moreVacanciesViewModel.vacancies.observe(viewLifecycleOwner){ data->

            if (data != null) {
                if (data.isEmpty()){
                    activity?.let { showToast(it.getString(R.string.no_vacancies)) }
                }
                vacanciesAdapter.reload(data)
                binding.vacanciesCount.text = "${moreVacanciesViewModel.countMultipleVacancies(data.count())}"
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