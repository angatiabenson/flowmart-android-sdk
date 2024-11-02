package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.data.models.response.user.UserResponse
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.domain.util.isEmailValid
import ke.co.banit.flowmartsdk.domain.util.isPasswordStrong
import ke.co.banit.flowmartsdk.domain.util.isPhoneValid
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
 * Use case for registering a new user in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [UserRepository] to handle user registration,
 * providing a consistent and reusable approach for user onboarding.
 *
 * @property repository An instance of [UserRepository] used to handle user registration.
 */
internal class RegisterUserUseCase(private val repository: UserRepository) {

    /**
     * Executes the use case to register a new user.
     *
     * Validates the provided details, ensuring that the name is not blank, the email format is valid,
     * the phone number is correctly formatted, and the password meets strength requirements.
     *
     * @param name The full name of the user.
     * @param email The email address of the user.
     * @param phone The phone number of the user.
     * @param password The password chosen by the user, which must meet strength criteria.
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<UserResponse, Exception> {
        if (name.isBlank()) return Result.Error(Exception("Name cannot be blank"))
        if (!isEmailValid(email)) return Result.Error(Exception("Invalid email format"))
        if (!isPhoneValid(phone)) return Result.Error(Exception("Invalid phone number format"))
        if (!isPasswordStrong(password)) return Result.Error(Exception("Password must be at least 8 characters, contain a number, an uppercase letter, and a special character"))

        return runCatchingResult {
            repository.registerUser(name, email, phone, password)
        }.handleResult()
    }
}
