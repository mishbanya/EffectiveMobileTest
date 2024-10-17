package com.mishbanya.effectivemobiletest.data.common.repositoryimpl

import com.mishbanya.effectivemobiletest.BuildConfig
import com.mishbanya.effectivemobiletest.domain.common.entity.ResponseData
import com.mishbanya.effectivemobiletest.domain.common.repository.IOffersAndVacanciesRepository
import com.mishbanya.effectivemobiletest.domain.common.services.IOffersAndVacanciesService
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class OffersAndVacanciesRepositoryImpl @Inject constructor(
    private val offersAndVacanciesService: IOffersAndVacanciesService
): IOffersAndVacanciesRepository {
    override fun getOffersAndVacancies(): Single<Response<ResponseData>> {
        val fileUrl = BuildConfig.GET_DATA_URL
        return offersAndVacanciesService.getOffersAndVacancies(fileUrl)
    }
}