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
 * Use case for retrieving products by category from the repository.
 * @property repository An instance of ProductRepository to fetch products.
 */
class GetProductsByCategoryUseCase(private val repository: ProductRepository) {
    /**
     * Executes the use case to retrieve products by a specific category.
     * @param categoryId The ID of the category to filter products.
     * @return Result<List<Product>, Exception> - A result wrapping a list of products or an exception on failure.
     */
    suspend operator fun invoke(categoryId: Int): Result<List<Product>, Exception> {
        return repository.getProductsByCategory(categoryId)
    }
}