package ke.co.banit.flowmartsdk.domain.util

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * A generic class that represents a value or an error.
 *
 * @param T The type of the value.
 */
sealed class Result<out T> {

    /**
     * Represents a successful outcome with a value.
     *
     * @param T The type of the value.
     * @property data The successful result data.
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents a failure outcome with an exception.
     *
     * @property exception The exception that caused the failure.
     */
    data class Error(val exception: Throwable) : Result<Nothing>()
}