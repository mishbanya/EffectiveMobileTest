package com.mishbanya.effectivemobiletest.data.vacancies.module

import android.util.Log
import com.mishbanya.effectivemobiletest.domain.vacancies.services.IVacanciesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JobsModule {
    @Provides
    @Singleton
    fun provideJobsService(
        retrofit: Retrofit
    ): IVacanciesService{
        Log.d("Hilt", "Creating IJobsService client instance")
        return retrofit.create(IVacanciesService::class.java)
    }
}