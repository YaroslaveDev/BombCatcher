package com.pfv.bombcatcher.ui.screens.settings_screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.model.Response
import com.pfv.bombcatcher.domain.use_cases.UseCases
import com.pfv.bombcatcher.repository.GameRepositoryImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    var gamerData by mutableStateOf<GamerData>(GamerData())

    fun getUserData(){
        viewModelScope.launch {

            useCases.getUserData().collect{
                when(it){
                    is Response.Failure -> {

                    }
                    Response.Loading -> {

                    }
                    is Response.Success -> {
                        gamerData = it.data
                    }
                }
            }


        }
    }
}