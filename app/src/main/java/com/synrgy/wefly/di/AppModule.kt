package com.synrgy.wefly.di

import com.synrgy.wefly.data.api.ApiConfig
import com.synrgy.wefly.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun getApiService(): ApiService = ApiConfig.getApiService()
}