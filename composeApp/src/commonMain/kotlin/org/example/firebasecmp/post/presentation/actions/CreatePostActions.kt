package org.example.firebasecmp.post.presentation.actions

sealed interface CreatePostActions{
    data class OnContentChanged(val content: String) : CreatePostActions
    data object OnTapBack : CreatePostActions
    data object OnTapCreatePost: CreatePostActions
    data object OnErrorDialogDismissed: CreatePostActions
}