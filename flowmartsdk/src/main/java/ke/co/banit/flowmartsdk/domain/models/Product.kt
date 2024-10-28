package ke.co.banit.flowmartsdk.domain.models

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */
data class Product(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val quantity: Int,
)
