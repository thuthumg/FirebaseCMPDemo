package org.example.firebasecmp.users.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserVO(
    @SerialName("uid")
    val uid: String,
    @SerialName("email")
    val email: String,
    @SerialName("userName")
    val userName: String
)
