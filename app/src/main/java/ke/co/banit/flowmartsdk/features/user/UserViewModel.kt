package ke.co.banit.flowmartsdk.features.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ke.co.banit.flowmartsdk.util.PreferencesManager
import ke.co.banit.flowmartsdk.util.initializeSdk
import ke.co.banit.flowmartsdk.util.onError
import ke.co.banit.flowmartsdk.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<UserUiState>(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState

    private val preferencesManager = PreferencesManager.getInstance(application)

    // Register user
    fun registerUser(
        name: String, email: String, phone: String, password: String
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val sdk = initializeSdk()
                sdk.registerUser(name, email, phone, password).onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = response.message,
                    )
                }.onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to create user: ${exception.message}"
                    )
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to create user: ${exception.message}"
                )
            }
        }
    }

    // Login user and store in DataStore on success
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val sdk = initializeSdk()
                sdk.loginUser(email, password).onSuccess { response ->
                    preferencesManager.saveApiKey(response.apiKey)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = response.message,
                    )
                }.onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to authenticate user: ${exception.message}"
                    )
                }
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to authenticate user: ${exception.message}"
                )
            }
        }
    }

    // Fetch user
    fun fetchUser() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val apiKey = preferencesManager.apiKey.first()
                val sdk = initializeSdk(apiKey)
                sdk.getUserProfile().onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false, user = response.user
                    )
                }.onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch user: ${exception.message}"
                    )
                }
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to fetch user: ${exception.message}"
                )
            }
        }
    }

    // Update user
    fun updateUser(
        name: String?, email: String?, phone: String?, password: String?
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val apiKey = preferencesManager.apiKey.first()
                val sdk = initializeSdk(apiKey)
                sdk.updateUserProfile(name, email, phone, password).onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false, successMessage = response.message
                    )
                }.onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to update user: ${exception.message}"
                    )
                }
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to update user: ${exception.message}"
                )
            }
        }
    }

    // Delete user
    fun deleteUser(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val apiKey = preferencesManager.apiKey.first()
                val sdk = initializeSdk(apiKey)
                sdk.deleteUserAccount().onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false, successMessage = response.message
                    )
                }.onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to delete user: ${exception.message}"
                    )
                }
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to delete user: ${exception.message}"
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = UserUiState(user = _uiState.value.user)
    }
}