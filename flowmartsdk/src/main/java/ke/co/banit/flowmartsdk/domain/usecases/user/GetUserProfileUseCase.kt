package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.data.models.response.user.UserProfileResponse
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Use case for retrieving the user profile.
 * @property repository An instance of UserRepository to fetch user profile data.
 */
class GetUserProfileUseCase(private val repository: UserRepository) {
    /**
     * Executes the use case to retrieve the user profile.
     * @return Result<User, Exception> - A result wrapping the user profile or an exception on failure.
     */
    suspend operator fun invoke(): Result<UserProfileResponse, Exception> {
        return runCatchingResult {
            repository.getUserProfile()
        }.handleResult()
    }
}