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
 * Use case for retrieving all products from the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [ProductRepository] to fetch all products,
 * providing a consistent and reusable approach to retrieving product data.
 *
 * @property repository An instance of [ProductRepository] to access product data.
 */
internal class GetAllProductsUseCase(private val repository: ProductRepository) {

    /**
     * Executes the use case to retrieve all products.
     *
     * @return A [Result] containing either [ProductsListResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(): Result<ProductsListResponse, Exception> {
        return runCatchingResult {
            repository.getAllProducts()
        }.handleResult()
    }
}
