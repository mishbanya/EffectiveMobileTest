package com.mishbanya.effectivemobiletest.domain.vacancies.services

import com.mishbanya.effectivemobiletest.domain.common.entity.ResponseData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface IVacanciesService {
    @GET
    fun downloadFile(@Url fileUrl: String): Observable<ResponseData>
}