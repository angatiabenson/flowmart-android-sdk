package ke.co.banit.flowmartsdk.data.models.response

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */
internal data class ApiResponse<T>(
    val status: String,
    val message: String,
    val code: Int? = null,
    val data: T? = null
)