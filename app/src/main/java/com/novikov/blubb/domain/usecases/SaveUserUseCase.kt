package com.novikov.blubb.domain.usecases

import com.novikov.blubb.data.UserRepositoryImpl
import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.repositories.UserRepository

class SaveUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(user: User){
        userRepository.saveUser(user)
    }

}