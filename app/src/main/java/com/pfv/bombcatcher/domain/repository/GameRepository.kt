package com.pfv.bombcatcher.domain.repository

import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getAllUsersData(): Flow<Response<List<GamerData>>>
    fun getUserData(): Flow<Response<GamerData>>
    suspend fun addGamerData(gamerData: GamerData): Response<Boolean>
}