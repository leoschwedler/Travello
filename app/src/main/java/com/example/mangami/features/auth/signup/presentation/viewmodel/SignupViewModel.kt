package com.example.mangami.features.auth.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangami.features.auth.signup.presentation.event.SignupEvent
import com.example.mangami.features.auth.signup.presentation.state.SignupState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    val _uiState = MutableStateFlow(SignupState())
    val uiState = _uiState.asStateFlow()

    val _uiEvent = Channel<SignupEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onSubmit(username: String, email: String, password: String) {
        viewModelScope.launch {
            if (isValid(username = username, email = email, password = password)) {
                _uiState.update { it.copy(isLoading = true) }
            }
        }
    }

    suspend fun isValid(username: String, email: String, password: String): Boolean {
        if (username.isBlank() || username.length < 3) {
            _uiEvent.send(SignupEvent.showSnackbar("Invalid username"))
            return false
        }
        if (email.isBlank()) {
            _uiEvent.send(SignupEvent.showSnackbar("Invalid email"))
            return false
        }
        if (password.isBlank() || password.length < 6) {
            _uiEvent.send(SignupEvent.showSnackbar("Invalid password"))
            return false
        }
        return true
    }

    fun toggleRadionButton(){
        _uiState.update { it.copy(isChecked = !it.isChecked) }
    }


}


