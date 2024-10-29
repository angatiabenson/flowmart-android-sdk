package ke.co.banit.flowmartsdk.data.models.response.user

import com.squareup.moshi.Json
import ke.co.banit.flowmartsdk.data.models.dto.User

data class LoginUserResponse(

    @Json(name = "api_key") val apiKey: String,
    val message: String,
    val status: String,
    val user: User
)