package com.pfv.bombcatcher.domain.use_cases

import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.repository.GameRepository

class AddUserData(
    private val repository: GameRepository
) {
    suspend operator fun invoke(
        gamerData: GamerData
    ) = repository.addGamerData(gamerData)
}