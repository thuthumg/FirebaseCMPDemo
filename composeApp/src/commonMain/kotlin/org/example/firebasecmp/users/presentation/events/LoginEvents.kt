package org.example.firebasecmp.users.presentation.events

sealed interface LoginEvents{
    data object NavigateToRegister : LoginEvents
    data object NavigateToHome : LoginEvents
}