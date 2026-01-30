package org.example.firebasecmp.post.presentation.state

import org.example.firebasecmp.users.data.vos.UserVO

data class CreatePostState(
    val content: String = "",
    val loggedInUser : UserVO? = null,
    val isLoading: Boolean = false,
    val errMsg: String = ""
)