package com.pfv.bombcatcher.ui.screens.game_over_screen

import android.annotation.SuppressLint
import android.provider.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.pfv.bombcatcher.App
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.use_cases.UseCases
import com.pfv.bombcatcher.ui.screens.lead_board_screen.LeadBoardScreenState
import com.pfv.bombcatcher.utils.TopPlayerPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class GameOverScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var firestore: FirebaseFirestore = Firebase.firestore
    var screenState by mutableStateOf<GameOverScreenState>(GameOverScreenState.InitialState)
    var gamerData by mutableStateOf<GamerData?>(GamerData(1, 0))
        private set

    var showLeadBoardScreen by mutableStateOf(false)
    var allGamersData = mutableStateListOf<GamerData>()
    var showAuthScreen by mutableStateOf(false)
    val isUserSignedIn = FirebaseAuth.getInstance().currentUser

    fun addGameData(data: GamerData) = viewModelScope.launch {

        if (((data.score ?: 0) >= (gamerData?.score ?: 0)) && (isUserSignedIn != null)) {
            useCases.addUserData(
                data.copy(
                    countOfGames = gamerData?.countOfGames ?: (0 + 1),
                )
            )

        } else {
            if (isUserSignedIn != null){
                useCases.addUserData(
                    data.copy(
                        score = gamerData?.score ?: 0,
                        countOfGames = gamerData?.countOfGames ?: (0 + 1),
                    )

                )
            }
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

    suspend fun getAllGamersData(score: String) {
        allGamersData.addAll(
            firestore.collection("game_data").orderBy("score", Query.Direction.DESCENDING).get()
                .await()
                .toObjects(GamerData::class.java)
        )

        if (((gamerData?.score ?: 0) < score.toInt()) && (isUserSignedIn != null)){
            processScoreData(score)
        }
    }

    private suspend fun processScoreData(score: String){

        screenState = GameOverScreenState.SetupState
        delay(500)

        try {
            if (
                ((gamerData?.score ?: 0) < score.toInt()) &&
                allGamersData[0].score!! < score.toInt()
            ) {
                screenState = GameOverScreenState.TopPlayer(TopPlayerPosition.FIRST)
            } else if (
                ((gamerData?.score ?: 0) < score.toInt()) &&
                allGamersData[1].score!! < score.toInt()
            ) {
                screenState = GameOverScreenState.TopPlayer(TopPlayerPosition.SECOND)
            } else if (
                ((gamerData?.score ?: 0) < score.toInt()) &&
                allGamersData[2].score!! < score.toInt()
            ) {
                screenState = GameOverScreenState.TopPlayer(TopPlayerPosition.THIRD)
            } else if ((gamerData?.score ?: 0) < score.toInt()) {
                screenState = GameOverScreenState.NewRecord
            }
        }catch (e: IndexOutOfBoundsException){
            screenState = GameOverScreenState.InitialState
        }
    }

    fun clearData(){
        screenState = GameOverScreenState.InitialState
        gamerData = GamerData(1, 0)
        showLeadBoardScreen = false
        allGamersData.clear()
    }
}