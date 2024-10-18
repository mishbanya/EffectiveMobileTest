package com.mishbanya.effectivemobiletest.data.vacancies.repositoryimpl

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.mishbanya.effectivemobiletest.domain.common.entity.SharedPreferenceKeys.ACCESS_TOKEN_KEY
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesSaverRepository
import javax.inject.Inject

class VacanciesSaverRepositoryImpl@Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
    ): IVacanciesSaverRepository  {
    override fun saveVacancies(vacancies: List<VacancyModel>?): Boolean {
        if (vacancies != null) {
            if (vacancies.isEmpty()) return false
            Log.d("SharedPreferences", "Vacancies get successful.")
            val jsonVacancyList = gson.toJson(vacancies)
            return try {
                Log.d("SharedPreferences", "Saving vacancies")
                with(sharedPreferences.edit()) {
                    putString(ACCESS_TOKEN_KEY, jsonVacancyList)
                    apply()
                }
                true
            } catch (e: Exception) {
                Log.e("SharedPreferences", "Error saving vacancies", e)
                false
            }
        }else{
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
            Log.d("SharedPreferences", "Vacancies cleared")
            return true
        }
    }
}