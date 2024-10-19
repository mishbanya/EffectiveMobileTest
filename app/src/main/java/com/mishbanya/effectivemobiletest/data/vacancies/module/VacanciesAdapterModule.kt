package com.mishbanya.effectivemobiletest.data.vacancies.module

import com.mishbanya.effectivemobiletest.domain.common.repository.IMultipleLangRepository
import com.mishbanya.effectivemobiletest.presentation.adapters.VacanciesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object VacanciesAdapterModule {

    @Provides
    fun provideVacanciesAdapter(
        multipleLangRepository: IMultipleLangRepository
    ): VacanciesAdapter {
        return VacanciesAdapter(multipleLangRepository)
    }
}