package com.example.mangami.features.auth.signup.presentation.event

sealed class SignupEvent {
    data class showSnackbar(val message: String) : SignupEvent()
}