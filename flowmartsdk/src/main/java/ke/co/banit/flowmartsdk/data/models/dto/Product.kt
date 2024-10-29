package ke.co.banit.flowmartsdk.data.models.dto

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */
data class Product(
    val category: Category,
    val id: Int,
    val name: String,
    val quantity: String
)
