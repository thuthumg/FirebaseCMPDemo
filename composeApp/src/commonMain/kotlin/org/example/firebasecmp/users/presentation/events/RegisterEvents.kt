package org.example.firebasecmp.users.presentation.events

sealed interface RegisterEvents {
    data object NavigateToHome: RegisterEvents
    data object NavigateToLogin: RegisterEvents
}
