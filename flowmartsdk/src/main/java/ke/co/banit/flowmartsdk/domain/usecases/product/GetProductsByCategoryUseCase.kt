package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.domain.repositories.ProductRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult

/**
 *  FlowMart SDK
 *
 *  Copyright (c) 2024 Angatia Benson
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

/**
 * Use case for retrieving products by category from the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [ProductRepository] to fetch products
 * belonging to a specific category, providing a consistent and reusable approach for category-based product retrieval.
 *
 * @property repository An instance of [ProductRepository] used to fetch products by category.
 */
internal class GetProductsByCategoryUseCase(private val repository: ProductRepository) {

    /**
     * Executes the use case to retrieve products for a specific category.
     *
     * @param categoryId The ID of the category to filter products by.
     * @return A [Result] containing either [ProductsListResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(categoryId: Int): Result<ProductsListResponse, Exception> {
        return runCatchingResult {
            repository.getProductsByCategory(categoryId)
        }.handleResult()
    }
}
