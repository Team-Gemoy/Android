package com.synrgy.wefly.domain

interface PreferenceRepository {
    fun getToken(): String

    suspend fun setToken(value: String)

    suspend fun clearToken()
}