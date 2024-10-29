package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.data.models.response.product.CreateProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.DeleteProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsByCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
import ke.co.banit.flowmartsdk.util.Result

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */

interface ProductRepository {
    suspend fun getAllProducts(): Result<ProductsListResponse, Exception>
    suspend fun createProduct(
        categoryId: Int,
        name: String,
        quantity: Int
    ): Result<CreateProductResponse, Exception>

    suspend fun getProductsByCategory(categoryId: Int): Result<ProductsByCategoryResponse, Exception>
    suspend fun updateProduct(
        id: Int,
        name: String,
        quantity: Int
    ): Result<UpdateProductResponse, Exception>

    suspend fun deleteProduct(id: Int): Result<DeleteProductResponse, Exception>
}