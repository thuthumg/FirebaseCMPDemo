package org.example.firebasecmp.post.presentation.events

sealed interface PostDetailEvents {
    data object NavigateToPostList: PostDetailEvents
}