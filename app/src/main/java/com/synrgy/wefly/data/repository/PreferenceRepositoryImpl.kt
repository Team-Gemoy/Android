package com.synrgy.wefly.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.synrgy.wefly.common.PreferenceDefaults
import com.synrgy.wefly.domain.PreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): PreferenceRepository {
    companion object {
        private val TOKEN = stringPreferencesKey("token")
    }

    override fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[TOKEN] ?: PreferenceDefaults.TOKEN
        }
    }

    override suspend fun setToken(value: String) {
        try {
            dataStore.edit {
                it[TOKEN] = value
                Log.d("neotica-token", "Token saved: $value")
            }
        } catch (e: Exception) {
            Log.e("neotica-token", "Error saving token: ${e.message}", e)
        }
    }

    override suspend fun clearToken() {
        withContext(Dispatchers.IO) {
            dataStore.edit {
                it.clear()
            }
        }
    }
}