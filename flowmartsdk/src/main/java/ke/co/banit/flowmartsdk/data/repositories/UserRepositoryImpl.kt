package ke.co.banit.flowmartsdk.data.repositories

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserResponse
import ke.co.banit.flowmartsdk.data.remote.api.UserApiService
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
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
 * Implementation of [UserRepository] for managing user-related operations within the FlowMart API.
 *
 * This class uses [UserApiService] to perform network operations for users, including registration,
 * login, profile management, and account deletion. It handles API responses, error handling, and provides
 * results wrapped in [Result] to indicate success or failure.
 *
 * @property userApiService An instance of [UserApiService] used to make user-related API calls.
 */
internal class UserRepositoryImpl(private val userApiService: UserApiService) : UserRepository {

    /**
     * Registers a new user in the FlowMart API.
     *
     * @param name The name of the user to register.
     * @param email The email address of the user to register.
     * @param phone The phone number of the user to register.
     * @param password The password for the user’s account.
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<UserResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("name", name)
                .addFormDataPart("phone", phone)
                .addFormDataPart("password", password)
            val requestBody = builder.build()

            val response = userApiService.registerUser(body = requestBody)
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
     * Logs in an existing user to the FlowMart API.
     *
     * @param email The email address of the user.
     * @param password The password for the user’s account.
     * @return A [Result] containing either [LoginUserResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun loginUser(
        email: String,
        password: String
    ): Result<LoginUserResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
            val requestBody = builder.build()

            val response = userApiService.loginUser(body = requestBody)
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
     * Retrieves the profile details of the currently authenticated user.
     *
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun getUserProfile(): Result<UserResponse, Exception> {
        return try {
            val response = userApiService.getUserProfile()
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
     * Updates the profile of the currently authenticated user.
     *
     * @param name The updated name of the user (optional).
     * @param email The updated email address of the user (optional).
     * @param phone The updated phone number of the user (optional).
     * @param password The updated password for the user’s account (optional).
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun updateUserProfile(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UserResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
            if (name != null) builder.addFormDataPart("name", name)
            if (email != null) builder.addFormDataPart("email", email)
            if (phone != null) builder.addFormDataPart("phone", phone)
            if (password != null) builder.addFormDataPart("password", password)
            val requestBody = builder.build()

            val response = userApiService.updateUserProfile(body = requestBody)
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
     * Deletes the account of the currently authenticated user.
     *
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun deleteUserAccount(): Result<BaseResponse, Exception> {
        return try {
            val response = userApiService.deleteUserAccount()
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
