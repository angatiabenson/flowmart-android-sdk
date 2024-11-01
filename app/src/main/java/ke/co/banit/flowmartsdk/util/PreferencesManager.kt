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

class PreferencesManager(private val context: Context) {

    // Define the DataStore instance
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

    // Define keys for the stored values
    companion object {
        private val API_KEY = stringPreferencesKey("api_key")
    }

    // Save API Key
    suspend fun saveApiKey(apiKey: String) {
        context.dataStore.edit { preferences ->
            preferences[API_KEY] = apiKey
        }
    }

    // Read API Key as a Flow
    val apiKey: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[API_KEY]
        }
}