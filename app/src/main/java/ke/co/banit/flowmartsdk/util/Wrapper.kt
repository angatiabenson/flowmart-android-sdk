package ke.co.banit.flowmartsdk.util

import ke.co.banit.flowmartsdk.FlowMartSdk

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */

fun initializeSdk(apiKey: String? = null): FlowMartSdk {
    // Use the API key to configure SDK here
    return FlowMartSdk.Builder()
        .configure(
            configuration = FlowMartConfiguration(
                apiKey = apiKey
            ),
            environment = Environment.DEVELOPMENT
        )
        .build()
}