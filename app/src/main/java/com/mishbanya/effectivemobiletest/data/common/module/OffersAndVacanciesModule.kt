package com.mishbanya.effectivemobiletest.data.common.module

import android.util.Log
import com.mishbanya.effectivemobiletest.data.common.repositoryimpl.OffersAndVacanciesRepositoryImpl
import com.mishbanya.effectivemobiletest.domain.common.repository.IOffersAndVacanciesRepository
import com.mishbanya.effectivemobiletest.domain.common.services.IOffersAndVacanciesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OffersAndVacanciesModule {
    @Provides
    @Singleton
    fun provideOffersAndVacanciesService(
        retrofit: Retrofit
    ): IOffersAndVacanciesService {
        Log.d("Hilt", "Creating IOffersAndVacanciesService client instance")
        return retrofit.create(IOffersAndVacanciesService::class.java)
    }

    @Provides
    @Singleton
    fun provideOffersAndVacanciesRepositoryImpl(
        offersService: IOffersAndVacanciesService
    ): IOffersAndVacanciesRepository {
        Log.d("Hilt", "Creating IOffersAndVacanciesRepository client instance")
        return OffersAndVacanciesRepositoryImpl(offersService)
    }
}