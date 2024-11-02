package ke.co.banit.flowmartsdk.data.remote.api

import ke.co.banit.flowmartsdk.data.models.response.ApiResponse
import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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
 * Service interface defining API calls related to product management within the FlowMart API.
 *
 * This interface provides endpoints for fetching, creating, updating, and deleting products,
 * as well as retrieving products by category.
 */
internal interface ProductApiService {

    /**
     * Retrieves a list of all products available in the FlowMart inventory.
     *
     * @return A [Response] wrapping an [ApiResponse] containing [ProductsListResponse] with details of each product.
     */
    @GET("products")
    suspend fun getAllProducts(): Response<ApiResponse<ProductsListResponse>>

    /**
     * Creates a new product in the FlowMart inventory.
     *
     * @param body The request payload containing details of the product to create.
     * @return A [Response] wrapping an [ApiResponse] containing [BaseResponse], which provides a message indicating
     *         the success or failure of the product creation.
     */
    @POST("products")
    suspend fun createProduct(@Body body: RequestBody): Response<ApiResponse<BaseResponse>>

    /**
     * Retrieves all products belonging to a specific category in the FlowMart inventory.
     *
     * @param categoryId The unique identifier of the category for which products are being fetched.
     * @return A [Response] wrapping an [ApiResponse] containing [ProductsListResponse] with details of each product in the specified category.
     */
    @GET("categories/{category_id}/products")
    suspend fun getProductsByCategory(@Path("category_id") categoryId: Int): Response<ApiResponse<ProductsListResponse>>

    /**
     * Updates an existing product in the FlowMart inventory.
     *
     * @param id The unique identifier of the product to update.
     * @param product The request payload containing updated details for the specified product.
     * @return A [Response] wrapping an [ApiResponse] containing [UpdateProductResponse], which includes
     *         the updated product details and a success message.
     */
    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: RequestBody
    ): Response<ApiResponse<UpdateProductResponse>>

    /**
     * Deletes a product from the FlowMart inventory.
     *
     * @param id The unique identifier of the product to delete.
     * @return A [Response] wrapping an [ApiResponse] containing [BaseResponse], which provides a message
     *         indicating the success or failure of the deletion operation.
     */
    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<ApiResponse<BaseResponse>>
}
