package ke.co.banit.flowmartsdk.data.repositories

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
import ke.co.banit.flowmartsdk.data.remote.api.ProductApiService
import ke.co.banit.flowmartsdk.domain.repositories.ProductRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleApiError
import okhttp3.MultipartBody

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
 * Implementation of [ProductRepository] for managing product operations within the FlowMart API.
 *
 * This class uses [ProductApiService] to perform network operations for products, including fetching,
 * creating, updating, and deleting products, as well as fetching products by category. It handles API responses,
 * error handling, and provides results wrapped in [Result] to indicate success or failure.
 *
 * @property productApiService An instance of [ProductApiService] used to make product-related API calls.
 */
internal class ProductRepositoryImpl(private val productApiService: ProductApiService) :
    ProductRepository {

    /**
     * Retrieves a list of all products from the FlowMart API.
     *
     * @return A [Result] containing either [ProductsListResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun getAllProducts(): Result<ProductsListResponse, Exception> {
        return try {
            val response = productApiService.getAllProducts()
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Creates a new product in the FlowMart API.
     *
     * @param categoryId The ID of the category to which the product belongs.
     * @param name The name of the product to be created.
     * @param quantity The quantity of the product.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun createProduct(
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<BaseResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("category_id", categoryId.toString())
                .addFormDataPart("name", name)
                .addFormDataPart("quantity", quantity)
            val requestBody = builder.build()

            val response = productApiService.createProduct(body = requestBody)
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Retrieves all products belonging to a specific category from the FlowMart API.
     *
     * @param categoryId The ID of the category for which products are being fetched.
     * @return A [Result] containing either [ProductsListResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun getProductsByCategory(categoryId: Int): Result<ProductsListResponse, Exception> {
        return try {
            val response = productApiService.getProductsByCategory(categoryId = categoryId)
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Updates an existing product in the FlowMart API.
     *
     * @param productId The unique identifier of the product to update.
     * @param categoryId The ID of the category to which the product belongs.
     * @param name The updated name of the product.
     * @param quantity The updated quantity of the product.
     * @return A [Result] containing either [UpdateProductResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun updateProduct(
        productId: Int,
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<UpdateProductResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("category_id", categoryId.toString())
                .addFormDataPart("name", name)
                .addFormDataPart("quantity", quantity)
            val requestBody = builder.build()

            val response = productApiService.updateProduct(id = productId, product = requestBody)
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Deletes a product from the FlowMart API.
     *
     * @param productId The unique identifier of the product to delete.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun deleteProduct(productId: Int): Result<BaseResponse, Exception> {
        return try {
            val response = productApiService.deleteProduct(id = productId)
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
