package ke.co.banit.flowmartsdk.data.remote

import ke.co.banit.flowmartsdk.data.remote.api.CategoryApiService
import ke.co.banit.flowmartsdk.data.remote.api.ProductApiService
import ke.co.banit.flowmartsdk.data.remote.api.UserApiService
import ke.co.banit.flowmartsdk.util.Settings
import ke.co.banit.flowmartsdk.util.createMoshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */
internal object ApiClient {

    fun getCategoryAPIService(baseUrl: String, interceptor: Interceptor): CategoryApiService =
        getRetrofit(baseUrl, interceptor).create(CategoryApiService::class.java)

    fun getProductAPIService(baseUrl: String, interceptor: Interceptor): ProductApiService =
        getRetrofit(baseUrl, interceptor).create(ProductApiService::class.java)

    fun getUserAPIService(baseUrl: String, interceptor: Interceptor): UserApiService =
        getRetrofit(baseUrl, interceptor).create(UserApiService::class.java)

    private fun getRetrofit(baseUrl: String, interceptor: Interceptor): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val builder = OkHttpClient.Builder()
        val client = builder.connectTimeout(Settings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Settings.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Settings.READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()
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