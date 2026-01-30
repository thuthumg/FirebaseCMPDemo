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
import org.example.firebasecmp.post.presentation.actions.PostActions
import org.example.firebasecmp.post.presentation.events.PostEvents
import org.example.firebasecmp.post.presentation.state.PostState
import org.example.firebasecmp.users.data.repository.AuthRepository

class PostViewModel : ViewModel(){
    private val repo = PostsRepository
    private val authRepo = AuthRepository

    //State
    private val _state = MutableStateFlow(PostState())

    val state = _state.asStateFlow()

    //Event
    private val _events = MutableSharedFlow<PostEvents>()
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch {

         repo.getPostsRealtime()
               .collect { posts ->
                   _state.update {
                       it.copy(posts = posts)
                   }
               }

        }

        viewModelScope.launch {
            try {
                val loggedInUser = authRepo.getLoggedInUser()
                _state.update {
                    it.copy(
                        user = loggedInUser
                    )
                }

            }catch (e: Exception){
                _state.update { it.copy(error = e.message ?: "Something went wrong.") }
            }
        }
    }

    fun handleAction(action: PostActions){
        when(action){
            is PostActions.OnTapComment -> TODO()
            is PostActions.OnTapDelete -> TODO()
            is PostActions.OnTapEdit -> TODO()
            is PostActions.OnTapLike -> TODO()
            is PostActions.OnTapPost -> {
                viewModelScope.launch {
                    _events.emit(PostEvents.NavigateToPostDetails(action.postId))
                }
            }
            PostActions.OnTapCreatePost -> {
                viewModelScope.launch {
                    _events.emit(PostEvents.NavigateToCreatePost)
                }
            }
        }
    }
}