package com.pfv.bombcatcher.ui.screens.game_over_screen

import android.annotation.SuppressLint
import android.provider.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pfv.bombcatcher.App
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class GameOverScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var firestore: FirebaseFirestore = Firebase.firestore
    var screenState by mutableStateOf<GameOverScreenState>(GameOverScreenState.SetupState)
    var gamerData by mutableStateOf<GamerData?>(GamerData(1, 0))
        private set
    var showLeadBoardScreen by mutableStateOf(false)

    fun addGameData(data: GamerData) = viewModelScope.launch {

        if ((data.score ?: 0) > (gamerData?.score?:0)) {
            useCases.addUserData(
                data.copy(
                    countOfGames = gamerData?.countOfGames ?: (0 + 1),
                )
            )

        } else {
            useCases.addUserData(
                data.copy(
                    score = gamerData?.score?:0,
                    countOfGames = gamerData?.countOfGames ?: (0 + 1),
                )

            )
        }

    }

    suspend fun getTask() {
        gamerData = currentCollection().get().await().toObject()
    }

    @SuppressLint("HardwareIds")
    private fun currentCollection(): DocumentReference =
        firestore.collection("game_data").document(
            Settings.Secure.getString(
                App.context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        )
}