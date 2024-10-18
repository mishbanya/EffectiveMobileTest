package com.mishbanya.effectivemobiletest.data.vacancies.repositoryimpl

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mishbanya.effectivemobiletest.domain.common.entity.SharedPreferenceKeys.ACCESS_TOKEN_KEY
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesGetterRepository
import javax.inject.Inject

class VacanciesGetterRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
): IVacanciesGetterRepository {
    override fun getVacancies(): List<VacancyModel>? {
        val jsonVacancyList = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        return if (jsonVacancyList != null) {
            Log.d("SharedPreferences", "Vacancies get successful.")
            val type = object : TypeToken<List<VacancyModel>>() {}.type
            gson.fromJson(jsonVacancyList, type)
        } else {
            Log.d("SharedPreferences", "Vacancies get failed.")
            null
        }
    }
}