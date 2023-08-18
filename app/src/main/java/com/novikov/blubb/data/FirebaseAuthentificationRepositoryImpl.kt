package com.novikov.blubb.data

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository

class FirebaseAuthentificationRepositoryImpl():FirebaseAuthentificationRepository {
    override suspend fun authentification(email: String, password: String): Boolean {
        val auth = Firebase.auth
        var isLogin = false

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            isLogin = it.isSuccessful
            Log.i("auth", it.isSuccessful.toString())
        }
        return isLogin
    }
}