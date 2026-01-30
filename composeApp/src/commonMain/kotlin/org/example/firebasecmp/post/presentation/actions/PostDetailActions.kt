package org.example.firebasecmp.post.presentation.actions

sealed interface PostDetailActions {

    data object OnTapBack: PostDetailActions

    data object OnErrorDialogDismissed: PostDetailActions
}