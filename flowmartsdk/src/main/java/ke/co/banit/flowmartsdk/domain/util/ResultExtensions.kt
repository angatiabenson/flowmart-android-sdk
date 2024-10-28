package ke.co.banit.flowmartsdk.domain.util

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Executes the given block and returns a Result object.
 *
 * @param block The block to execute.
 * @return Result.Success with the block's result or Result.Error if an exception is thrown.
 */
inline fun <T> runCatchingResult(block: () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: Throwable) {
        Result.Error(e)
    }
}

/**
 * Transforms the successful value using the given transform function.
 *
 * @param transform The function to apply to the successful value.
 * @return A new Result with the transformed value or the original error.
 */
inline fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(this.data))
        is Result.Error -> this
    }
}

/**
 * Executes the given transform function on the successful value and returns a new Result.
 *
 * @param transform The function to apply to the successful value.
 * @return The transformed Result or the original error.
 */
inline fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>): Result<R> {
    return when (this) {
        is Result.Success -> transform(this.data)
        is Result.Error -> this
    }
}

/**
 * Executes the given action if the Result is a success.
 *
 * @param action The action to execute with the successful value.
 */
inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(this.data)
    return this
}

/**
 * Executes the given action if the Result is an error.
 *
 * @param action The action to execute with the exception.
 */
inline fun <T> Result<T>.onError(action: (Throwable) -> Unit): Result<T> {
    if (this is Result.Error) action(this.exception)
    return this
}