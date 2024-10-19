package com.mishbanya.effectivemobiletest.data.vacancies.module

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.mishbanya.effectivemobiletest.data.vacancies.repositoryimpl.ChangeVacancyFavoritenessRepositoryImpl
import com.mishbanya.effectivemobiletest.data.vacancies.repositoryimpl.VacanciesGetterRepositoryImpl
import com.mishbanya.effectivemobiletest.data.vacancies.repositoryimpl.VacanciesSaverRepositoryImpl
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IChangeVacancyFavoritenessRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesGetterRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesSaverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VacanciesModule {
    @Provides
    @Singleton
    fun provideVacanciesSaverRepositoryImpl(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): IVacanciesSaverRepository {
        Log.d("Hilt", "Creating VacanciesSaverRepositoryImpl client instance")
        return VacanciesSaverRepositoryImpl(sharedPreferences, gson)
    }
    @Provides
    @Singleton
    fun provideVacanciesGetterRepositoryImpl(
        sharedPreferences: SharedPreferences,
        gson: Gson
    ): IVacanciesGetterRepository {
        Log.d("Hilt", "Creating VacanciesGetterRepositoryImpl client instance")
        return VacanciesGetterRepositoryImpl(sharedPreferences, gson)
    }

    @Provides
    @Singleton
    fun provideChangeVacancyFavoritenessRepositoryImpl(
        vacanciesSaverRepository: IVacanciesSaverRepository,
        vacanciesGetterRepository: IVacanciesGetterRepository
    ): IChangeVacancyFavoritenessRepository {
        Log.d("Hilt", "Creating ChangeVacancyFavoritenessRepositoryImpl client instance")
        return ChangeVacancyFavoritenessRepositoryImpl(vacanciesSaverRepository, vacanciesGetterRepository)
    }
}