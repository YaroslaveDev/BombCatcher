package com.pfv.bombcatcher.ui.screens.lead_board_screen

import com.pfv.bombcatcher.domain.model.GamerData

sealed class LeadBoardScreenState {
    object LoadingState: LeadBoardScreenState()
    object Success: LeadBoardScreenState()
}