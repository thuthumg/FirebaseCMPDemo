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
import org.example.firebasecmp.post.presentation.actions.CreatePostActions
import org.example.firebasecmp.post.presentation.events.CreatePostEvents
import org.example.firebasecmp.post.presentation.screens.PostScreen
import org.example.firebasecmp.post.presentation.state.CreatePostState
import org.example.firebasecmp.users.data.repository.AuthRepository
import kotlin.math.log

class CreatePostViewModel : ViewModel(){
    private val repo = PostsRepository
    private val authRepo = AuthRepository
    //State
    private val _state = MutableStateFlow(CreatePostState())

    val state = _state.asStateFlow()

    //Events
    private val _events = MutableSharedFlow<CreatePostEvents>()
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            val loggedInUser = authRepo.getLoggedInUser()
            _state.update { it.copy(
                loggedInUser = loggedInUser
            ) }
        }
    }

    fun handleAction(actions: CreatePostActions){
        when(actions){
            is CreatePostActions.OnContentChanged -> {
                _state.update {
                    it.copy(
                        content = actions.content
                    )
                }
            }
            CreatePostActions.OnTapBack -> {
                viewModelScope.launch {
                    _events.emit(CreatePostEvents.NavigateBack)
                }
            }
            CreatePostActions.OnTapCreatePost -> {
                _state.update { it.copy(isLoading = true) }

                viewModelScope.launch {
                    try {
                        repo.createPost(
                            _state.value.content
                        )
                        _state.update { it.copy(isLoading = false) }
                        _events.emit(CreatePostEvents.NavigateBack)
                    }catch (e: Exception){
                        _state.update { it.copy(isLoading = false,
                            errMsg = e.message?: "Something went wrong.") }
                    }

                }

            }

            CreatePostActions.OnErrorDialogDismissed -> {
                _state.update { it.copy(errMsg = "") }
            }
        }
    }
}