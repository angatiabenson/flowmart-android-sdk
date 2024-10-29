package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.domain.models.Product
import ke.co.banit.flowmartsdk.domain.repositories.ProductRepository
import ke.co.banit.flowmartsdk.domain.util.Result

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
    suspend operator fun invoke(id: Int, name: String, quantity: Int): Result<Product, Exception> {
        return repository.updateProduct(id, name, quantity)
    }
}