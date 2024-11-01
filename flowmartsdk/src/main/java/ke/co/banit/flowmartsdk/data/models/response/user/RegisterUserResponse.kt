package ke.co.banit.flowmartsdk.data.models.response.user

import ke.co.banit.flowmartsdk.data.models.dto.User

data class RegisterUserResponse(
    val message: String,
    val user: User
)