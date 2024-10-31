package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.domain.repositories.ProductRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Use case for retrieving all products from the repository.
 * @property repository An instance of ProductRepository to access product data.
 */
internal class GetAllProductsUseCase(private val repository: ProductRepository) {
    /**
     * Executes the use case to retrieve all products.
     * @return Result<List<Product>, Exception> - A result wrapping a list of products or an exception on failure.
     */
    suspend operator fun invoke(): Result<ProductsListResponse, Exception> {
        return runCatchingResult {
            repository.getAllProducts()
        }.handleResult()
    }
}