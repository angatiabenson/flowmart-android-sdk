package ke.co.banit.flowmartsdk.exceptions

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */
/**
 * Represents an API-specific exception containing the error code and message.
 *
 * @property code The HTTP status code of the error.
 * @property message A descriptive message detailing the error.
 */
class ApiException(val code: Int, message: String?) : Exception(message)