package com.novikov.blubb.domain.repositories

import com.novikov.blubb.domain.models.User

interface UserRepository {

    suspend fun getUser(): User

    suspend fun saveUser(user: User)

    suspend fun createUser(user: User)

}