package ke.co.banit.flowmartsdk.data.repositories

import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CreateCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.category.DeleteCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
import ke.co.banit.flowmartsdk.data.remote.api.CategoryApiService
import ke.co.banit.flowmartsdk.domain.repositories.CategoryRepository
import ke.co.banit.flowmartsdk.exceptions.ApiException
import ke.co.banit.flowmartsdk.util.Result
import okhttp3.MultipartBody


/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */
class CategoryRepositoryImpl(private val categoryApiService: CategoryApiService) :
    CategoryRepository {
    override suspend fun getAllCategories(): Result<CategoriesListResponse, Exception> {
        return try {
            val response = categoryApiService.getAllCategories()
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

    override suspend fun createCategory(name: String): Result<CreateCategoryResponse, Exception> {
        return try {

            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            builder.addFormDataPart("name", name)
            val requestBody = builder.build()

            val response = categoryApiService.createCategory(body = requestBody)
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

    override suspend fun updateCategory(
        id: Int, name: String
    ): Result<UpdateCategoryResponse, Exception> {
        return try {

            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            builder.addFormDataPart("name", name)
            val requestBody = builder.build()

            val response = categoryApiService.updateCategory(id = id, body = requestBody)
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

    override suspend fun deleteCategory(id: Int): Result<DeleteCategoryResponse, Exception> {
        return try {
            val response = categoryApiService.deleteCategory(id)
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