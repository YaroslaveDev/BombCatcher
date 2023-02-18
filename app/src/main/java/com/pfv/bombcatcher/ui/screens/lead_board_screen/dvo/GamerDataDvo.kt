package com.pfv.bombcatcher.ui.screens.lead_board_screen.dvo

import com.pfv.bombcatcher.utils.TopPlayerPosition

data class GamerDataDvo(
    val score: Int? = 0,
    val countOfGames: Int? = 0,
    val firstName: String = "",
    val lastName: String = "",
    val userImg: String = "",
    val playerPosition: TopPlayerPosition = TopPlayerPosition.NONE
)
