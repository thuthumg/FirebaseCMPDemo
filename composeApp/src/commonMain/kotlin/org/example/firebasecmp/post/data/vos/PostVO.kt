package org.example.firebasecmp.post.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostVO(
    @SerialName("id")
    val id: Long,
    @SerialName("content")
    val content: String,
    @SerialName("post_user_name")
    val postUserName: String,
    @SerialName("post_date")
    val postDate: String
)