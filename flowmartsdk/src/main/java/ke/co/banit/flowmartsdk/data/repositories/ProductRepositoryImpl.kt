package ke.co.banit.flowmartsdk.data.repositories

import ke.co.banit.flowmartsdk.data.models.response.product.CreateProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.DeleteProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsByCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
import ke.co.banit.flowmartsdk.data.remote.api.ProductApiService
import ke.co.banit.flowmartsdk.domain.repositories.ProductRepository
import ke.co.banit.flowmartsdk.exceptions.ApiException
import ke.co.banit.flowmartsdk.util.Result
import okhttp3.MultipartBody

/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */
internal class ProductRepositoryImpl(private val productApiService: ProductApiService) :
    ProductRepository {
    override suspend fun getAllProducts(): Result<ProductsListResponse, Exception> {
        return try {
            val response = productApiService.getAllProducts()
            if (response.isSuccessful) {
                val result = response.body()!!
                Result.Success(result)
            } else {
                Result.Error(ApiException(response.code(), response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun createProduct(
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<CreateProductResponse, Exception> {
        return try {

            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            builder.addFormDataPart("category_id", categoryId.toString())
            builder.addFormDataPart("name", name)
            builder.addFormDataPart("quantity", quantity)
            val requestBody = builder.build()

            val response = productApiService.createProduct(body = requestBody)
            if (response.isSuccessful) {
                val result = response.body()!!
                Result.Success(result)
            } else {
                Result.Error(ApiException(response.code(), response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getProductsByCategory(categoryId: Int): Result<ProductsByCategoryResponse, Exception> {
        return try {
            val response = productApiService.getProductsByCategory(categoryId = categoryId)
            if (response.isSuccessful) {
                val result = response.body()!!
                Result.Success(result)
            } else {
                Result.Error(ApiException(response.code(), response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateProduct(
        productId: Int,
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<UpdateProductResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            builder.addFormDataPart("category_id", categoryId.toString())
            builder.addFormDataPart("name", name)
            builder.addFormDataPart("quantity", quantity)
            val requestBody = builder.build()

            val response = productApiService.updateProduct(id = productId, product = requestBody)
            if (response.isSuccessful) {
                val result = response.body()!!
                Result.Success(result)
            } else {
                Result.Error(ApiException(response.code(), response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteProduct(productId: Int): Result<DeleteProductResponse, Exception> {
        return try {
            val response = productApiService.deleteProduct(id = productId)
            if (response.isSuccessful) {
                val result = response.body()!!
                Result.Success(result)
            } else {
                Result.Error(ApiException(response.code(), response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}