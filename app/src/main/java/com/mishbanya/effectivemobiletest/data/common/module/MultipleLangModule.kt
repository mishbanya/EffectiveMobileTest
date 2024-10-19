package com.mishbanya.effectivemobiletest.data.common.module

import android.util.Log
import com.mishbanya.effectivemobiletest.domain.common.repository.IMultipleLangRepository
import com.mishbanya.effectivemobiletest.domain.common.repositoryimpl.MultipleLangRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MultipleLangModule {
    @Provides
    @Singleton
    fun provideMultipleLangRepositoryImpl() : IMultipleLangRepository {
        Log.d("Hilt", "Creating MultipleLangRepositoryImpl client instance")
        return MultipleLangRepositoryImpl()
    }
}