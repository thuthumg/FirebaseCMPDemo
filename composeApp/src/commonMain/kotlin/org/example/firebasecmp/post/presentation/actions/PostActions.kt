package org.example.firebasecmp.post.presentation.actions

sealed interface PostActions {
    data class OnTapPost(val postId: String) : PostActions
    data class OnTapLike(val postId: Int) : PostActions
    data class OnTapComment(val postId : Int) : PostActions
    data class OnTapEdit(val postId: Int): PostActions
    data class OnTapDelete(val postId: Int): PostActions

    data object OnTapCreatePost : PostActions

}