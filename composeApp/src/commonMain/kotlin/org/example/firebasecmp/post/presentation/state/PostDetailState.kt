package org.example.firebasecmp.post.presentation.state

import org.example.firebasecmp.post.data.vos.PostVO

data class PostDetailState(
    val post : PostVO? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)