package com.mishbanya.effectivemobiletest.domain.vacancies.repository

import com.mishbanya.effectivemobiletest.domain.common.entity.ResponseData
import io.reactivex.rxjava3.core.Observable

interface IVacanciesRepository {
    fun getVacancies(): Observable<ResponseData>
}