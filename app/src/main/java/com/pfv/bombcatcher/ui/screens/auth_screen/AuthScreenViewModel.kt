package com.pfv.bombcatcher.ui.screens.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor(): ViewModel() {

    fun signWithCredential(credential: AuthCredential) = viewModelScope.launch {
        try {
            //loadingState.emit(LoadingState.LOADING)
            Firebase.auth.signInWithCredential(credential).await()
            //loadingState.emit(LoadingState.LOADED)
        } catch (e: Exception) {
            //loadingState.emit(LoadingState.error(e.localizedMessage))
        }
    }
}