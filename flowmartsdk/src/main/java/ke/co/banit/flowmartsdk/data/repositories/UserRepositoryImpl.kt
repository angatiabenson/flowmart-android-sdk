package ke.co.banit.flowmartsdk.data.repositories

import ke.co.banit.flowmartsdk.data.models.response.user.DeleteUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.RegisterUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UpdateUserProfileResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserProfileResponse
import ke.co.banit.flowmartsdk.data.remote.api.UserApiService
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.exceptions.ApiException
import ke.co.banit.flowmartsdk.util.Result
import okhttp3.MultipartBody

/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */
class UserRepositoryImpl(private val userApiService: UserApiService) :
    UserRepository {
    override suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<RegisterUserResponse, Exception> {
        return try {

            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            builder.addFormDataPart("email", email)
            builder.addFormDataPart("name", name)
            builder.addFormDataPart("phone", phone)
            builder.addFormDataPart("password", password)
            val requestBody = builder.build()

            val response = userApiService.registerUser(body = requestBody)
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

    override suspend fun loginUser(
        email: String,
        password: String
    ): Result<LoginUserResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            builder.addFormDataPart("email", email)
            builder.addFormDataPart("password", password)
            val requestBody = builder.build()

            val response = userApiService.loginUser(body = requestBody)
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

    override suspend fun getUserProfile(): Result<UserProfileResponse, Exception> {
        return try {
            val response = userApiService.getUserProfile()
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

    override suspend fun updateUserProfile(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UpdateUserProfileResponse, Exception> {
        return try {

            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            if (name != null) builder.addFormDataPart("name", name)
            if (email != null) builder.addFormDataPart("email", email)
            if (phone != null) builder.addFormDataPart("phone", phone)
            if (password != null) builder.addFormDataPart("password", password)
            val requestBody = builder.build()

            val response = userApiService.updateUserProfile(body = requestBody)
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

    override suspend fun deleteUserAccount(): Result<DeleteUserResponse, Exception> {
        return try {
            val response = userApiService.deleteUserAccount()
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