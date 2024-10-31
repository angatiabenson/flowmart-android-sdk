package ke.co.banit.flowmartsdk.util

import ke.co.banit.flowmartsdk.exceptions.ApiException
import ke.co.banit.flowmartsdk.exceptions.NetworkException
import retrofit2.HttpException
import java.io.IOException

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */
/**
 * Transforms the successful value using the provided [map] function.
 *
 * @param R The type of the transformed result.
 * @param map The function to transform the successful data.
 * @return A new [ke.co.banit.flowmartsdk.util.Result] containing the transformed data or the original error.
 */
inline fun <T, E : Exception, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Converts a [ke.co.banit.flowmartsdk.util.Result] with data into a [EmptyResult], ignoring the data.
 *
 * @return A new [EmptyResult] representing the same success or error state.
 */
fun <T, E : Exception> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map { }
}

/**
 * Executes the given [action] if the [ke.co.banit.flowmartsdk.util.Result] is a [Success].
 *
 * @param action The lambda to execute with the successful data.
 * @return The original [ke.co.banit.flowmartsdk.util.Result] after executing the action.
 */
inline fun <T, E : Exception> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

/**
 * Executes the given [action] if the [ke.co.banit.flowmartsdk.util.Result] is an [Error].
 *
 * @param action The lambda to execute with the error exception.
 * @return The original [ke.co.banit.flowmartsdk.util.Result] after executing the action.
 */
inline fun <T, E : Exception> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}

fun <T> Result<T, Exception>.handleResult(): Result<T, Exception> {
    return when (this) {
        is Result.Success -> this
        is Result.Error -> when (val exception = this.error) {
            is IOException -> Result.Error(NetworkException(exception.message ?: "Network error"))
            is HttpException -> {
                Result.Error(ApiException(exception.code(), exception.message()))
            }
            else -> Result.Error(exception)
        }
    }
}