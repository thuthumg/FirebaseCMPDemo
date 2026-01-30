package org.example.firebasecmp.users.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.firebasecmp.users.data.repository.AuthRepository
import org.example.firebasecmp.users.presentation.actions.RegisterActions
import org.example.firebasecmp.users.presentation.events.RegisterEvents
import org.example.firebasecmp.users.presentation.state.RegisterState

class RegisterViewModel: ViewModel(){

    private val repo = AuthRepository

    //State
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    //Events
    private val _events = MutableSharedFlow<RegisterEvents>()
    val events = _events.asSharedFlow()


    fun handleActions(actions: RegisterActions){
        when(actions){
            is RegisterActions.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = actions.email
                    )
                }
            }
            is RegisterActions.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = actions.password
                    )
                }
            }
            is RegisterActions.OnUserNameChanged -> {
                _state.update {
                    it.copy(
                        userName = actions.userName
                    )
                }
            }
            RegisterActions.OnTapRegister -> {


                if(_state.value.userName.isEmpty()){
                    _state.update {
                        it.copy(
                            errMsg = "User Name cannot be empty."
                        )
                    }
                    return
                }


                if(_state.value.email.isEmpty()){
                    _state.update {
                        it.copy(
                            errMsg = "Email cannot be empty."
                        )
                    }

                    return
                }
                if(_state.value.password.isEmpty()){
                    _state.update {
                        it.copy(
                            errMsg = "Password cannot be empty."
                        )
                    }
                    return
                }


                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }

                viewModelScope.launch {
                    try {
                        repo.register(
                            _state.value.email, _state.value.password,
                            _state.value.userName
                        )
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        _events.emit(RegisterEvents.NavigateToHome)

                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errMsg = e.message ?: "Something went wrong."
                            )
                        }
                    }
                }

            }
            RegisterActions.OnTapBack -> {
                viewModelScope.launch {
                    _events.emit(RegisterEvents.NavigateToLogin)
                }
            }

            RegisterActions.OnErrorDialogDismissed -> {
                _state.update {
                    it.copy(errMsg = "")
                }
            }
        }
    }
}