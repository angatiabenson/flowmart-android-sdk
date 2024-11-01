package ke.co.banit.flowmartsdk.features.user

import ke.co.banit.flowmartsdk.data.models.dto.User

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */
data class UserUiState(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val user: User? = null,
)