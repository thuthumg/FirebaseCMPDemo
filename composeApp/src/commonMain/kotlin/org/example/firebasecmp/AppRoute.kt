package org.example.firebasecmp

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute{

    @Serializable
    data object Login: AppRoute()

    @Serializable
    data object Register : AppRoute()

    @Serializable
    data object Home: AppRoute()

    @Serializable
    data class PostDetails(val postId : String) : AppRoute()

    @Serializable
    data object CreatePost: AppRoute()

}