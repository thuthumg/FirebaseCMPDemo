package org.example.firebasecmp.post.presentation.events

sealed interface PostEvents{
    data class NavigateToPostDetails(val postId: String): PostEvents
    data object NavigateToCreatePost: PostEvents
}