package com.mishbanya.effectivemobiletest.domain.vacancies.repository

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody

interface IVacanciesRepository {
    fun getVacancies(): Observable<ResponseBody>
}