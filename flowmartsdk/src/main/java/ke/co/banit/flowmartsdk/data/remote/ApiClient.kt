package ke.co.banit.flowmartsdk.data.remote

import ke.co.banit.flowmartsdk.data.remote.api.CategoryApiService
import ke.co.banit.flowmartsdk.data.remote.api.ProductApiService
import ke.co.banit.flowmartsdk.data.remote.api.UserApiService
import ke.co.banit.flowmartsdk.util.Environment
import ke.co.banit.flowmartsdk.util.Settings
import ke.co.banit.flowmartsdk.util.createMoshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

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
 * A singleton object for creating API service instances within the FlowMart API SDK.
 *
 * The `ApiClient` provides functions to initialize and retrieve API service interfaces for
 * categories, products, and users, each configured with a specific base URL, interceptor, and environment.
 */
internal object ApiClient {

    /**
     * Retrieves an instance of [CategoryApiService] for accessing category-related API endpoints.
     *
     * @param baseUrl The base URL for the FlowMart API.
     * @param interceptor An [Interceptor] for handling authentication or other custom headers.
     * @param environment The [Environment] in which the API client is running, used to configure logging.
     * @return A configured instance of [CategoryApiService].
     */
    fun getCategoryAPIService(
        baseUrl: String,
        interceptor: Interceptor,
        environment: Environment
    ): CategoryApiService =
        getRetrofit(baseUrl, interceptor, environment).create(CategoryApiService::class.java)

    /**
     * Retrieves an instance of [ProductApiService] for accessing product-related API endpoints.
     *
     * @param baseUrl The base URL for the FlowMart API.
     * @param interceptor An [Interceptor] for handling authentication or other custom headers.
     * @param environment The [Environment] in which the API client is running, used to configure logging.
     * @return A configured instance of [ProductApiService].
     */
    fun getProductAPIService(
        baseUrl: String,
        interceptor: Interceptor,
        environment: Environment
    ): ProductApiService =
        getRetrofit(baseUrl, interceptor, environment).create(ProductApiService::class.java)

    /**
     * Retrieves an instance of [UserApiService] for accessing user-related API endpoints.
     *
     * @param baseUrl The base URL for the FlowMart API.
     * @param interceptor An [Interceptor] for handling authentication or other custom headers.
     * @param environment The [Environment] in which the API client is running, used to configure logging.
     * @return A configured instance of [UserApiService].
     */
    fun getUserAPIService(
        baseUrl: String,
        interceptor: Interceptor,
        environment: Environment
    ): UserApiService =
        getRetrofit(baseUrl, interceptor, environment).create(UserApiService::class.java)

    /**
     * Configures and retrieves a [Retrofit] instance to be used for creating API services.
     *
     * @param baseUrl The base URL for the FlowMart API.
     * @param interceptor An [Interceptor] for handling authentication or other custom headers.
     * @param environment The [Environment] in which the API client is running, determining if logging is enabled.
     * @return A configured [Retrofit] instance for making network requests.
     */
    private fun getRetrofit(
        baseUrl: String,
        interceptor: Interceptor,
        environment: Environment
    ): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val builder = OkHttpClient.Builder()
            .connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if (environment == Environment.DEVELOPMENT) {
            builder.addInterceptor(httpLoggingInterceptor)
        }

        val client = builder.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    createMoshi()
                )
            )
            .client(client)
            .build()
    }
}
