package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
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
 * Use case for creating a new product in a specific category within the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [ProductRepository] to add a new product,
 * providing a consistent and reusable approach for product creation.
 *
 * @property repository An instance of [ProductRepository] used to handle product creation.
 */
internal class CreateProductUseCase(private val repository: ProductRepository) {

    /**
     * Executes the use case to create a new product.
     *
     * @param categoryId The ID of the category where the product will be added.
     * @param name The name of the product to be created.
     * @param quantity The initial quantity of the product.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<BaseResponse, Exception> {
        return runCatchingResult {
            repository.createProduct(categoryId, name, quantity)
        }.handleResult()
    }
}
