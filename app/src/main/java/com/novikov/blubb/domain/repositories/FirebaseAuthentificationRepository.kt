package com.novikov.blubb.domain.repositories

interface FirebaseAuthentificationRepository {
    suspend fun authentification(email: String, password:String): Boolean
    suspend fun logOut()
}