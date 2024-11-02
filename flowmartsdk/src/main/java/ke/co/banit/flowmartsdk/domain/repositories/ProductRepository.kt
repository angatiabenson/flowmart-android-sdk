package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
import ke.co.banit.flowmartsdk.util.Result

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
 * Interface defining repository operations for managing products within the FlowMart API.
 *
 * The `ProductRepository` provides abstract functions to handle fetching, creating, updating,
 * and deleting products, as well as retrieving products by category. Each operation returns
 * results wrapped in [Result] to indicate success or failure.
 */
internal interface ProductRepository {

    /**
     * Retrieves a list of all products from the FlowMart API.
     *
     * @return A [Result] containing either [ProductsListResponse] if successful, or an [Exception] on failure.
     */
    suspend fun getAllProducts(): Result<ProductsListResponse, Exception>

    /**
     * Creates a new product in the FlowMart API.
     *
     * @param categoryId The ID of the category to which the product belongs.
     * @param name The name of the product to be created.
     * @param quantity The quantity of the product.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend fun createProduct(
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<BaseResponse, Exception>

    /**
     * Retrieves all products belonging to a specific category from the FlowMart API.
     *
     * @param categoryId The ID of the category for which products are being fetched.
     * @return A [Result] containing either [ProductsListResponse] if successful, or an [Exception] on failure.
     */
    suspend fun getProductsByCategory(categoryId: Int): Result<ProductsListResponse, Exception>

    /**
     * Updates an existing product in the FlowMart API.
     *
     * @param productId The unique identifier of the product to update.
     * @param categoryId The ID of the category to which the product belongs.
     * @param name The updated name of the product.
     * @param quantity The updated quantity of the product.
     * @return A [Result] containing either [UpdateProductResponse] if successful, or an [Exception] on failure.
     */
    suspend fun updateProduct(
        productId: Int,
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<UpdateProductResponse, Exception>

    /**
     * Deletes a product from the FlowMart API.
     *
     * @param productId The unique identifier of the product to delete.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend fun deleteProduct(productId: Int): Result<BaseResponse, Exception>
}
