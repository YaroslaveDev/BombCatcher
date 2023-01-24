package com.pfv.bombcatcher.repository

import com.google.firebase.firestore.CollectionReference
import com.pfv.bombcatcher.domain.model.GamerData
import com.pfv.bombcatcher.domain.model.Response
import com.pfv.bombcatcher.domain.repository.GameRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImplementation @Inject constructor(
    private val gameDataRef: CollectionReference
): GameRepository {



    override fun getUserData(): Flow<Response<GamerData>> = callbackFlow {
        val snapshotListener = gameDataRef.orderBy("id").addSnapshotListener { snapshot, e ->
            val booksResponse = if (snapshot != null) {
                val gamerData = snapshot.toObjects(GamerData::class.java).first()
                Response.Success(gamerData)
            } else {
                Response.Failure(e)
            }
            trySend(booksResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addGamerData(gamerData: GamerData): Response<Boolean> {
        return try {
            val id = gameDataRef.document().id
            val gamerData = GamerData(
                score = gamerData.score,
                countOfGames = gamerData.countOfGames
            )
            gameDataRef.document(id).set(gamerData).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

}