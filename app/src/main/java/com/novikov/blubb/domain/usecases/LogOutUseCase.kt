package com.novikov.blubb.domain.usecases

import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository

class LogOutUseCase(private val firebaseAuthentificationRepository: FirebaseAuthentificationRepository) {
    suspend fun execute(){
        firebaseAuthentificationRepository.logOut()
    }
}