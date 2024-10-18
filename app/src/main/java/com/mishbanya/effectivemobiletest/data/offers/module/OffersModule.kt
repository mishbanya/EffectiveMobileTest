package com.mishbanya.effectivemobiletest.data.offers.module

import android.util.Log
import com.mishbanya.effectivemobiletest.domain.common.services.IOffersAndVacanciesService
import com.mishbanya.effectivemobiletest.domain.offers.repository.IOfferLinkOpenerRepository
import com.mishbanya.effectivemobiletest.domain.offers.repositoryimpl.OfferLinkOpenerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OffersModule {
    @Provides
    @Singleton
    fun provideOfferLinkOpenerRepositoryImpl(
    ): IOfferLinkOpenerRepository {
        Log.d("Hilt", "Creating provideOfferLinkOpenerRepositoryImpl client instance")
        return OfferLinkOpenerRepositoryImpl()
    }
}