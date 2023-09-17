package com.novikov.blubb.domain.usecases

import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.repositories.UserRepository

class CreateUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(user:User){
        userRepository.createUser(user)
    }

}