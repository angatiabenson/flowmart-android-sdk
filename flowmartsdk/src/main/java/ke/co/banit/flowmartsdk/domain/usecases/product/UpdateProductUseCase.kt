package ke.co.banit.flowmartsdk.domain.usecases.product

import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
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
 * Use case for updating an existing product in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [ProductRepository] to update a product's details,
 * providing a consistent and reusable approach for product updates.
 *
 * @property repository An instance of [ProductRepository] used to handle product updates.
 */
internal class UpdateProductUseCase(private val repository: ProductRepository) {

    /**
     * Executes the use case to update an existing product.
     *
     * @param productId The unique identifier of the product to update.
     * @param categoryId The ID of the category to which the product belongs.
     * @param name The updated name of the product.
     * @param quantity The updated quantity of the product.
     * @return A [Result] containing either [UpdateProductResponse] if successful, or an [Exception] on failure.
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
