package ke.co.banit.flowmartsdk.util

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Executes the given [block] and returns a [Result].
 *
 * @param T The type of the successful result.
 * @param E The type of the error exception.
 * @param block The block of code to execute.
 * @return A [Result.Success] if the block executes successfully, or [Result.Error] if an exception is thrown.
 */
inline fun <T> runCatchingResult(block: () -> Result<T, Exception>): Result<T, Exception> {
    return try {
        block()
    } catch (e: Exception) {
        Result.Error(e)
    }
}
