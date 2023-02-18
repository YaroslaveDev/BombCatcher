package com.pfv.bombcatcher.ui.screens.lead_board_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LeadBoardViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    var allGamersData by mutableStateOf<List<GamerData>>(emptyList())
        private set
    private var firestore: FirebaseFirestore = Firebase.firestore

    suspend fun getAllGamersData(){
        allGamersData = firestore.collection("game_data").orderBy("score", Query.Direction.DESCENDING).get().await()
            .toObjects(GamerData::class.java)
    }

}