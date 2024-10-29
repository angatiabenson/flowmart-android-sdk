package ke.co.banit.flowmartsdk.data.models.dto

import com.squareup.moshi.Json

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    @Json(name = "created_at") val createdAt: String
)
