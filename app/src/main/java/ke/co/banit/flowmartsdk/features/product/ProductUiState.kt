package ke.co.banit.flowmartsdk.features.product

import ke.co.banit.flowmartsdk.data.models.dto.Category
import ke.co.banit.flowmartsdk.data.models.dto.Product

/**
 * @Author: Angatia Benson
 * @Date: 11/2/2024
 * Copyright (c) 2024 BanIT
 */
data class ProductUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList(),
)
