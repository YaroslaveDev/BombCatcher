package com.pfv.bombcatcher.domain.use_cases

import kotlinx.serialization.Serializable

@Serializable
data class UseCases(
    val getUserData: GetUserData,
    val addUserData: AddUserData,
)