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
 * Use case for updating the user's profile in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [UserRepository] to update the user's profile details,
 * providing a consistent and reusable approach for profile modifications.
 *
 * @property repository An instance of [UserRepository] used to handle profile updates.
 */
internal class UpdateUserProfileUseCase(private val repository: UserRepository) {

    /**
     * Executes the use case to update the user's profile.
     *
     * Validates provided details if they are not null, ensuring that the name is not blank,
     * the email format is valid, the phone number is correctly formatted, and the password meets strength requirements.
     *
     * @param name The updated name of the user (nullable).
     * @param email The updated email address of the user (nullable).
     * @param phone The updated phone number of the user (nullable).
     * @param password The updated password of the user, which must meet strength criteria if provided (nullable).
     * @return A [Result] containing either [UserResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UserResponse, Exception> {
        // Check if name is provided and validate it
        if (!name.isNullOrEmpty() && name.isBlank()) {
            return Result.Error(Exception("Name cannot be blank"))
        }

        // Check if email is provided and validate format
        if (!email.isNullOrEmpty() && !isEmailValid(email)) {
            return Result.Error(Exception("Invalid email format"))
        }

        // Check if phone is provided and validate format
        if (!phone.isNullOrEmpty() && !isPhoneValid(phone)) {
            return Result.Error(Exception("Invalid phone number format"))
        }

        // Check if password is provided and validate strength
        if (!password.isNullOrEmpty() && !isPasswordStrong(password)) {
            return Result.Error(
                Exception("Password must be at least 8 characters, contain a number, an uppercase letter, and a special character")
            )
        }

        return runCatchingResult {
            repository.updateUserProfile(name, email, phone, password)
        }.handleResult()
    }
}
