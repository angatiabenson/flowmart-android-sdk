package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.domain.models.Product
import ke.co.banit.flowmartsdk.domain.util.Result

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */

interface ProductRepository {
    suspend fun getAllProducts(): Result<List<Product>, Exception>
    suspend fun createProduct(
        categoryId: Int,
        name: String,
        quantity: Int
    ): Result<Product, Exception>

    suspend fun getProductsByCategory(categoryId: Int): Result<List<Product>, Exception>
    suspend fun updateProduct(id: Int, name: String, quantity: Int): Result<Product, Exception>
    suspend fun deleteProduct(id: Int): Result<Unit, Exception>
}