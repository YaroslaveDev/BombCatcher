package com.pfv.bombcatcher.ui.screens.auth_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor(): ViewModel() {

    val authState = mutableStateOf<AuthScreenViewState>(AuthScreenViewState.Initial)

    fun signWithCredential(credential: AuthCredential) = viewModelScope.launch {
        try {
            authState.value = AuthScreenViewState.SetupState
            Firebase.auth.signInWithCredential(credential).await()
            authState.value = AuthScreenViewState.Success
        } catch (e: Exception) {
            authState.value = AuthScreenViewState.Error(message = e.localizedMessage.orEmpty())
        }
    }
}