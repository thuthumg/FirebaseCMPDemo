package org.example.firebasecmp.users.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.firebasecmp.users.data.repository.AuthRepository
import org.example.firebasecmp.users.presentation.actions.LoginActions
import org.example.firebasecmp.users.presentation.events.LoginEvents
import org.example.firebasecmp.users.presentation.state.LoginState

class LoginViewModel : ViewModel(){
    private val repo = AuthRepository

    //State
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    //Events
    private val _events = MutableSharedFlow<LoginEvents>()
    val events = _events.asSharedFlow()


    fun handleActions(actions: LoginActions){
        when(actions){
            is LoginActions.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = actions.email
                    )
                }
            }
            is LoginActions.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = actions.password
                    )
                }
            }
            LoginActions.OnTapLogin -> {
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
                        repo.login(
                            _state.value.email, _state.value.password
                        )
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        _events.emit(LoginEvents.NavigateToHome)

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
            LoginActions.OnTapSignUp -> {
                viewModelScope.launch {
                    _events.emit(LoginEvents.NavigateToRegister)
                }
            }

            LoginActions.OnErrorDialogDismissed -> {
                _state.update {
                    it.copy(errMsg = "")
                }
            }
        }
    }
}