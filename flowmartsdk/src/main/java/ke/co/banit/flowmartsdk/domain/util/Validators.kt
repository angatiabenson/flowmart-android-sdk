package ke.co.banit.flowmartsdk.domain.util

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

fun isEmailValid(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailRegex)
}

fun isPhoneValid(phone: String): Boolean {
    val phoneRegex = "^[+]?\\d{10,15}$".toRegex()
    return phone.matches(phoneRegex)
}

fun isPasswordStrong(password: String): Boolean {
    // Example: password should be at least 8 characters, contain a number, an uppercase letter, and a special character
    val passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}$".toRegex()
    return password.matches(passwordRegex)
}
