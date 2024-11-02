package ke.co.banit.flowmartsdk.features.product

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
 * @Date: 11/2/2024
 * Copyright (c) 2024 BanIT
 */

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState

    private val preferencesManager = PreferencesManager.getInstance(application)

    // Create product
    fun createProduct(name: String, categoryId: Int, quantity: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.createProduct(categoryId, name, quantity)
                .onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = response.message,
                    )
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to create product: ${exception.message}"
                    )
                }
        }
    }

    // Fetch products
    fun fetchProducts() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.getProducts()
                .onSuccess { response ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        products = response.products
                    )
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch products: ${exception.message}"
                    )
                }
        }
    }

    // Update product
    fun updateProduct(productId: Int, name: String, categoryId: Int, quantity: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.updateProduct(productId, categoryId, name, quantity)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Product updated successfully!"
                    )
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to update product: ${exception.message}"
                    )
                }
        }
    }

    // Delete product
    fun deleteProduct(productId: Int) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val apiKey = preferencesManager.apiKey.first()
            val sdk = initializeSdk(apiKey)
            sdk.deleteProduct(productId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "Product deleted successfully!"
                    )
                    fetchProducts() // Refresh list after deletion
                }
                .onError { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to delete product: ${exception.message}"
                    )
                }
        }
    }

    // Reset UI state (for dismissing messages)
    fun resetUiState() {
        _uiState.value = ProductUiState(
            categories = _uiState.value.categories,
            products = _uiState.value.products
        )
    }
}
