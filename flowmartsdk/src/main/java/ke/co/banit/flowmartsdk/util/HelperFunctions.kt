package ke.co.banit.flowmartsdk.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ke.co.banit.flowmartsdk.data.models.response.ApiResponse
import ke.co.banit.flowmartsdk.exceptions.ApiException
import retrofit2.Response

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
 * Creates and configures a [Moshi] instance for JSON serialization and deserialization.
 *
 * This function sets up a Moshi instance with a [KotlinJsonAdapterFactory] to support Kotlin data classes.
 *
 * @return A configured [Moshi] instance.
 */
internal fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

/**
 * Handles API error responses by parsing the error body into an [ApiException].
 *
 * This function attempts to parse the error response body using [Moshi] to convert it to an [ApiException].
 * If parsing fails or the error body is null, a generic [ApiException] is returned with the HTTP status code and
 * a default error message.
 *
 * @param response The API [Response] containing the error to be handled.
 * @return A [Result.Error] wrapping an [ApiException] with the parsed or default error details.
 */
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


