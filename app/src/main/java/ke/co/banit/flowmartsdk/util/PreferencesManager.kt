package ke.co.banit.flowmartsdk.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */
class PreferencesManager private constructor(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")
    private val dataStore: DataStore<Preferences> = context.applicationContext.dataStore

    // Define keys for the stored values
    companion object {
        private val API_KEY = stringPreferencesKey("api_key")

        // Singleton instance
        private var instance: PreferencesManager? = null

        // Thread-safe singleton instance creation
        fun getInstance(context: Context): PreferencesManager {
            return instance ?: synchronized(this) {
                instance ?: PreferencesManager(context.applicationContext).also { instance = it }
            }
        }
    }

    // Save API Key
    suspend fun saveApiKey(apiKey: String) {
        dataStore.edit { preferences ->
            preferences[API_KEY] = apiKey
        }
    }

    // Read API Key as a Flow
    val apiKey: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[API_KEY]
        }
}