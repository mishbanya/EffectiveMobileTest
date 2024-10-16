package com.mishbanya.effectivemobiletest.domain.vacancies.services

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface IVacanciesService {
    @GET
    fun downloadFile(@Url fileUrl: String): Observable<ResponseBody>
}