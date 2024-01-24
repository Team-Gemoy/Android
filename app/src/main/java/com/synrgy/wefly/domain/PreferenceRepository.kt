package com.synrgy.wefly.domain

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun getToken(): Flow<String>

    suspend fun setToken(value: String)

    suspend fun clearToken()
}