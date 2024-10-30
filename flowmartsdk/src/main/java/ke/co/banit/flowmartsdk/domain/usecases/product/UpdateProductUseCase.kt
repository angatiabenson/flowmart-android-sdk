package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
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
 * Use case for updating an existing product in the repository.
 * @property repository An instance of ProductRepository to handle product updates.
 */
class UpdateProductUseCase(private val repository: ProductRepository) {
    /**
     * Executes the use case to update an existing product.
     * @param id The unique identifier of the product to update.
     * @param name The updated name of the product.
     * @param quantity The updated quantity of the product.
     * @return Result<Product, Exception> - A result wrapping the updated product or an exception on failure.
     */
    suspend operator fun invoke(
        productId: Int,
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<UpdateProductResponse, Exception> {
        return runCatchingResult {
            repository.updateProduct(productId, categoryId, name, quantity)
        }.handleResult()
    }
}