package com.novikov.blubb.data

import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.repositories.UserRepository

class UserRepositoryImpl(): UserRepository {
    override suspend fun getUser(): User {
        TODO("djew")
    }

    override suspend fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}