package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.data.models.response.product.DeleteProductResponse
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
 * Use case for deleting a product in the repository.
 * @property repository An instance of ProductRepository to handle product deletion.
 */
internal class DeleteProductUseCase(private val repository: ProductRepository) {
    /**
     * Executes the use case to delete a product by its ID.
     * @param id The unique identifier of the product to delete.
     * @return Result<Unit, Exception> - A result wrapping a success unit or an exception on failure.
     */
    suspend operator fun invoke(id: Int): Result<DeleteProductResponse, Exception> {
        return runCatchingResult {
            repository.deleteProduct(id)
        }.handleResult()
    }
}