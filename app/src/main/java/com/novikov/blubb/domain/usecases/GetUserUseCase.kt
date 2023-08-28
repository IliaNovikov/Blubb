package com.novikov.blubb.domain.usecases

import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.repositories.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute() : User{
         return userRepository.getUser()
    }
}