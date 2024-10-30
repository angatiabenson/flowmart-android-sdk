package ke.co.banit.flowmartsdk.util

/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */
object Settings {
    /**
     * Connection timeout duration
     */
    const val CONNECT_TIMEOUT: Long = 60 * 1000

    /**
     * Connection Read timeout duration
     */
    const val READ_TIMEOUT: Long = 60 * 1000

    /**
     * Connection write timeout duration
     */
    const val WRITE_TIMEOUT: Long = 60 * 1000

    const val BASE_URL = "https://flowmart.co.ke/"
}