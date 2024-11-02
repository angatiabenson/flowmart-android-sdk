package ke.co.banit.flowmartsdk.domain.util

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
 * Validates the format of an email address.
 *
 * This function checks if the provided email string matches a standard email format.
 *
 * @param email The email address to validate.
 * @return `true` if the email format is valid, `false` otherwise.
 */
internal fun isEmailValid(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailRegex)
}

/**
 * Validates the format of a phone number.
 *
 * This function checks if the provided phone string is a valid phone number. The format allows for
 * an optional leading `+` followed by 10 to 15 digits, accommodating both local and international numbers.
 *
 * @param phone The phone number to validate.
 * @return `true` if the phone number format is valid, `false` otherwise.
 */
internal fun isPhoneValid(phone: String): Boolean {
    val phoneRegex = "^[+]?\\d{10,15}$".toRegex()
    return phone.matches(phoneRegex)
}

/**
 * Checks if a password meets strength requirements.
 *
 * This function verifies that the password has at least 8 characters, includes at least one digit, one uppercase letter,
 * and one special character, and contains no whitespace.
 *
 * @param password The password string to validate.
 * @return `true` if the password meets strength requirements, `false` otherwise.
 */
internal fun isPasswordStrong(password: String): Boolean {
    // Example: password should be at least 8 characters, contain a number, an uppercase letter, and a special character
    val passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}$".toRegex()
    return password.matches(passwordRegex)
}

