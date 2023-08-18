package com.novikov.blubb.domain.usecases

import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository

class AuthentificationUseCase(private val firebaseAuthentificationRepository: FirebaseAuthentificationRepository) {
    suspend fun execute(email: String, password: String){
        firebaseAuthentificationRepository.authentification(email = email, password = password)
    }
}