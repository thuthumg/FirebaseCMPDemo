package org.example.firebasecmp.users.presentation.state

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errMsg: String = ""
)