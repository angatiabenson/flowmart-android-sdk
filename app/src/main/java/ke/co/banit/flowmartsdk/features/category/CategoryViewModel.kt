package ke.co.banit.flowmartsdk.features.category

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
class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState

    private val preferencesManager = PreferencesManager(application)

    // Create category
    fun createCategory(name: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.createCategory(name)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Category created successfully!",
                    )
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to create category: ${exception.message}"
                    )
                }
        }
    }

    // Fetch categories
    fun fetchCategories() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.getCategories()
                .onSuccess { categories ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        categories = categories.categories
                    )
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch categories: ${exception.message}"
                    )
                }
        }
    }

    // Update category
    fun updateCategory(categoryId: Int, newName: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.updateCategory(categoryId, newName)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Category updated successfully!",
                    )
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to update category: ${exception.message}"
                    )
                }
        }
    }

    // Delete category
    fun deleteCategory(categoryId: Int) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.deleteCategory(categoryId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Category deleted successfully!"
                    )
                    fetchCategories() // Refresh list after deletion
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to delete category: ${exception.message}"
                    )
                }
        }
    }

    // Reset UI state (for dismissing messages)
    fun resetUiState() {
        _uiState.value = CategoryUiState(categories = _uiState.value.categories)
    }
}
