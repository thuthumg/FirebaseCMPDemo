package org.example.firebasecmp.post.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.firebasecmp.post.data.repositories.PostsRepository
import org.example.firebasecmp.post.presentation.actions.PostDetailActions
import org.example.firebasecmp.post.presentation.events.PostDetailEvents
import org.example.firebasecmp.post.presentation.state.PostDetailState

class PostDetailViewModel(postId: String): ViewModel() {

    val postRepo = PostsRepository

    private val _state = MutableStateFlow(PostDetailState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<PostDetailEvents>()
    val events = _event.asSharedFlow()

    init {
        _state.update {
            it.copy(
               isLoading = true
            )
        }
        viewModelScope.launch {

                try {
                    _state.update {
                        it.copy(
                            post = postRepo.getPost(postId),
                            isLoading = false
                        )
                    }

                }catch (e: Exception){
                    _state.update {
                        it.copy(
                            error = e.message ?: "Something went wrong.",
                            isLoading = false
                        )
                    }
                }


        }

    }


    fun handleAction(postDetailActions: PostDetailActions){
        when(postDetailActions){
            PostDetailActions.OnTapBack -> {
                viewModelScope.launch {
                    _event.emit(PostDetailEvents.NavigateToPostList)
                }

            }

            PostDetailActions.OnErrorDialogDismissed -> {
                _state.update {
                    it.copy(error = "")
                }
            }
        }
    }


}