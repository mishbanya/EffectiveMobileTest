package com.mishbanya.effectivemobiletest.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mishbanya.effectivemobiletest.R
import com.mishbanya.effectivemobiletest.domain.vacancies.usecases.IOnVacancyClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VacancyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vacancy, container, false)
    }
}