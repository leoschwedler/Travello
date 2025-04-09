package com.example.mangami.features.auth.signup.presentation.state

data class SignupState(
    val isChecked: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false
)
