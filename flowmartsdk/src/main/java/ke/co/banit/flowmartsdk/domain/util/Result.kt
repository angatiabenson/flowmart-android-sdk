package ke.co.banit.flowmartsdk.domain.util

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * A generic sealed interface that represents a success or an error outcome.
 *
 * @param D The type of the successful result data.
 * @param E The type of the error exception.
 */
sealed interface Result<out D, out E : Exception> {

    /**
     * Represents a successful outcome containing data.
     *
     * @param D The type of the successful result data.
     * @property data The successful result data.
     */
    data class Success<out D>(val data: D) : Result<D, Nothing>

    /**
     * Represents an error outcome containing an exception.
     *
     * @param E The type of the error exception.
     * @property error The exception that caused the error.
     */
    data class Error<out E : Exception>(val error: E) : Result<Nothing, E>
}

/**
 * A typealias for a Result with no data, used for operations that don't return a value.
 *
 * @param E The type of the error exception.
 */
typealias EmptyResult<E> = Result<Unit, E>