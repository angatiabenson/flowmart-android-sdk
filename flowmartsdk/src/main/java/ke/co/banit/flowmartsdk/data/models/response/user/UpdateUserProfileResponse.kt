package ke.co.banit.flowmartsdk.data.models.response.user

import ke.co.banit.flowmartsdk.data.models.dto.User

data class UpdateUserProfileResponse(
    val message: String,
    val status: String,
    val user: User
)