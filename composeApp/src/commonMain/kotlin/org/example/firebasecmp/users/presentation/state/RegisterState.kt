package org.example.firebasecmp.users.presentation.state

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val userName: String = "",
    val isLoading: Boolean = false,
    val errMsg: String = ""
)