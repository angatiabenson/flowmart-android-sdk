package ke.co.banit.flowmartsdk.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ke.co.banit.flowmartsdk.data.models.response.ApiResponse
import ke.co.banit.flowmartsdk.exceptions.ApiException
import retrofit2.Response

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */

internal fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

internal fun <T> handleApiError(response: Response<ApiResponse<T>>): Result.Error<ApiException> {
    val errorBody = response.errorBody()?.string()
    val errorResponse = errorBody?.let {
        val moshi = createMoshi()
        moshi.adapter(ApiException::class.java).fromJson(it)
    } ?: ApiException(
        code = response.code(),
        message = "Unknown error occurred. Please try again later."
    )

    return Result.Error(errorResponse)
}

