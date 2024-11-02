package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserResponse
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
 * Interface defining repository operations for managing user accounts within the FlowMart API.
 *
 * The `UserRepository` provides abstract functions to handle user registration, login,
 * profile retrieval and updates, and account deletion. Each operation returns results
 * wrapped in [Result] to indicate success or failure.
 */
internal interface UserRepository {

    /**
     * Registers a new user in the FlowMart API.
     *
     * @param name The full name of the user to register.
     * @param email The email address of the user to register.
     * @param phone The phone number of the user to register.
     * @param password The password for the user’s account.
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    suspend fun registerUser(
        name: String, email: String, phone: String, password: String
    ): Result<UserResponse, Exception>

    /**
     * Logs in an existing user to the FlowMart API.
     *
     * @param email The email address of the user.
     * @param password The password for the user’s account.
     * @return A [Result] containing either [LoginUserResponse] if successful (with an API token), or an [Exception] on failure.
     */
    suspend fun loginUser(
        email: String, password: String
    ): Result<LoginUserResponse, Exception>

    /**
     * Retrieves the profile details of the currently authenticated user.
     *
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    suspend fun getUserProfile(): Result<UserResponse, Exception>

    /**
     * Updates the profile of the currently authenticated user.
     *
     * @param name The updated name of the user (optional).
     * @param email The updated email address of the user (optional).
     * @param phone The updated phone number of the user (optional).
     * @param password The updated password for the user’s account (optional).
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    suspend fun updateUserProfile(
        name: String?, email: String?, phone: String?, password: String?
    ): Result<UserResponse, Exception>

    /**
     * Deletes the account of the currently authenticated user.
     *
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend fun deleteUserAccount(): Result<BaseResponse, Exception>
}
