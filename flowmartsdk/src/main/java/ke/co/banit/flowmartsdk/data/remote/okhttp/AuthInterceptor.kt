package ke.co.banit.flowmartsdk.data.remote.okhttp

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */
class AuthInterceptor(
    private val apiKey: String,
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .build()
        return chain.proceed(request)
    }
}