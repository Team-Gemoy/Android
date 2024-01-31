package com.synrgy.wefly.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.synrgy.wefly.data.api.ApiConfig
import com.synrgy.wefly.data.api.ApiService
import com.synrgy.wefly.data.repository.PreferenceRepositoryImpl
import com.synrgy.wefly.domain.PreferenceRepository
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
    fun getApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    @Singleton
    @Provides
    fun providePreferenceRepository(dataStore: DataStore<Preferences>): PreferenceRepository {
        return PreferenceRepositoryImpl(dataStore)
    }
}