package ke.co.banit.flowmartsdk.data.models.response.user

import ke.co.banit.flowmartsdk.data.models.dto.User

data class UserProfileResponse(
    val status: String,
    val user: User
)