package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.data.models.response.product.CreateProductResponse
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
 * Use case for creating a new product in a specific category.
 * @property repository An instance of ProductRepository to handle product creation.
 */
class CreateProductUseCase(private val repository: ProductRepository) {
    /**
     * Executes the use case to create a new product.
     * @param categoryId The ID of the category where the product will be added.
     * @param name The name of the product.
     * @param quantity The initial quantity of the product.
     * @return Result<Product, Exception> - A result wrapping the created product or an exception on failure.
     */
    suspend operator fun invoke(
        categoryId: Int,
        name: String,
        quantity: Int
    ): Result<CreateProductResponse, Exception> {
        return runCatchingResult {
            repository.createProduct(categoryId, name, quantity)
        }.handleResult()
    }
}