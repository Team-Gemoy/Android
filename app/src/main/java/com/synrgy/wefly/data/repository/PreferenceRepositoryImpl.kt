package com.synrgy.wefly.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.synrgy.wefly.common.PreferenceDefaults
import com.synrgy.wefly.domain.PreferenceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): PreferenceRepository {
    companion object {
        private val TOKEN = stringPreferencesKey("token")
    }

    private val data = dataStore.data
        .stateIn(
            scope = CoroutineScope(SupervisorJob()),
            started = SharingStarted.Eagerly,
            initialValue = runBlocking {
                dataStore.data.first()
            }
        )

    override fun getToken(): String {
        return data.value[TOKEN] ?: PreferenceDefaults.TOKEN
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