package org.example.firebasecmp.post.presentation.state

import org.example.firebasecmp.post.data.vos.PostVO
import org.example.firebasecmp.users.data.vos.UserVO

data class PostState(
    val posts : List<PostVO> = listOf(),
    val isLoading: Boolean = false,
    val error: String = "",
    val user: UserVO? = null
)
