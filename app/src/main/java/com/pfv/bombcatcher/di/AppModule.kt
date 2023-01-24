package com.pfv.bombcatcher.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pfv.bombcatcher.domain.repository.GameRepository
import com.pfv.bombcatcher.domain.use_cases.AddUserData
import com.pfv.bombcatcher.domain.use_cases.GetUserData
import com.pfv.bombcatcher.domain.use_cases.UseCases
import com.pfv.bombcatcher.repository.GameRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBooksRef() = Firebase.firestore.collection("game_data")

    @Provides
    fun provideBooksRepository(
        gameDataRef: CollectionReference
    ): GameRepository = GameRepositoryImplementation(gameDataRef)

    @Provides
    fun provideUseCases(
        repository: GameRepository
    ) = UseCases(
        getUserData = GetUserData(repository),
        addUserData = AddUserData(repository)
    )
}