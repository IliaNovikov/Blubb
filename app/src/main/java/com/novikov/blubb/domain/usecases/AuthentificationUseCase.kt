package com.novikov.blubb.domain.usecases

import android.util.Log
import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository

class AuthentificationUseCase(private val firebaseAuthentificationRepository: FirebaseAuthentificationRepository) {
    suspend fun execute(email: String, password: String): Boolean {
        Log.i("autnUseCase", firebaseAuthentificationRepository.authentification(email = email, password = password).toString())
        return firebaseAuthentificationRepository.authentification(email = email, password = password)
    }
}