package com.pfv.bombcatcher.domain.model

data class GamerData(
    val score: Int? = 0,
    val countOfGames: Int? = 0,
    val firstName: String = "",
    val lastName: String = "",
    val userImg: String = "",
    val userId: String = ""
)
