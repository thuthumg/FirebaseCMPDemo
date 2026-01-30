package org.example.firebasecmp.users.presentation.actions

sealed interface RegisterActions{
    data object OnTapRegister : RegisterActions
    data object OnTapBack : RegisterActions
    data class OnUserNameChanged(val userName: String) : RegisterActions

    data class OnEmailChanged(val email: String) : RegisterActions

    data class OnPasswordChanged(val password: String) : RegisterActions

    object OnErrorDialogDismissed : RegisterActions


}