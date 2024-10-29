package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.domain.models.User
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.domain.util.Result

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
    suspend operator fun invoke(): Result<User, Exception> {
        return repository.getUserProfile()
    }
}