package com.mishbanya.effectivemobiletest.data.vacancies.repositoryimpl

import com.mishbanya.effectivemobiletest.BuildConfig
import com.mishbanya.effectivemobiletest.domain.common.entity.ResponseData
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.services.IVacanciesService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class VacanciesRepositoryImpl @Inject constructor(
    private val vacanciesService:IVacanciesService
): IVacanciesRepository{
    override fun getVacancies(): Observable<ResponseData> {
        val fileUrl = BuildConfig.GET_DATA_URL
        return vacanciesService.downloadFile(fileUrl)
    }
}