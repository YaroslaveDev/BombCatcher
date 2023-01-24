package com.pfv.bombcatcher.domain.use_cases

import com.pfv.bombcatcher.domain.repository.GameRepository

class GetUserData (
    private val repository: GameRepository
) {
    operator fun invoke() = repository.getUserData()
}