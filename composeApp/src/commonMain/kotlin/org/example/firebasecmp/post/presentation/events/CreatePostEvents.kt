package org.example.firebasecmp.post.presentation.events

sealed interface CreatePostEvents {

    data object NavigateBack : CreatePostEvents
}