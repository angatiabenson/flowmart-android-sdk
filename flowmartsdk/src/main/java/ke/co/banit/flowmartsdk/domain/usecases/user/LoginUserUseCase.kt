package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.domain.util.isEmailValid
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
 * Use case for logging in a user in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [UserRepository] to handle user login,
 * providing a consistent and reusable approach for user authentication.
 *
 * @property repository An instance of [UserRepository] used to handle user login.
 */
internal class LoginUserUseCase(private val repository: UserRepository) {

    /**
     * Executes the use case to log in a user.
     *
     * Validates the email format and checks that the password is not blank before proceeding
     * with the login operation.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return A [Result] containing either [LoginUserResponse] (with the API token) if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<LoginUserResponse, Exception> {
        if (!isEmailValid(email)) return Result.Error(Exception("Invalid email format"))
        if (password.isBlank()) return Result.Error(Exception("Password cannot be blank"))

        return runCatchingResult {
            repository.loginUser(email, password)
        }.handleResult()
    }
}
