package ke.co.banit.flowmartsdk.features.category

import ke.co.banit.flowmartsdk.data.models.dto.Category

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */
data class CategoryUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null
)
