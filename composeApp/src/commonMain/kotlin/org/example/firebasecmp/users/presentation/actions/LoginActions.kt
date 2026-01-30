package org.example.firebasecmp.users.presentation.actions

sealed interface LoginActions{
    data object OnTapLogin : LoginActions
    data object OnTapSignUp : LoginActions

    data class OnEmailChanged(val email: String) : LoginActions

    data class OnPasswordChanged(val password: String) : LoginActions

    object OnErrorDialogDismissed : LoginActions


}