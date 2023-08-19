package com.novikov.blubb.data

import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.R
import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.repositories.UserRepository
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(): UserRepository {
    override suspend fun getUser(): User {
        TODO("djew")
    }

    override suspend fun saveUser(user: User) {
        val database = Firebase.database.reference
        val auth = Firebase.auth

        auth.createUserWithEmailAndPassword(user.email, user.password).await()

        user.id = auth.currentUser?.uid.toString()

        database.child("users").child(user.id).child("nickname").setValue(user.nickname)
        database.child("users").child(user.id).child("email").setValue(user.email)
        database.child("users").child(user.id).child("password").setValue(user.password)
        database.child("users").child(user.id).child("avatar").setValue(user.userImage.toString())
    }
}