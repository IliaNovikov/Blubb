package com.novikov.blubb.data

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository

class FirebaseAuthentificationRepositoryImpl():FirebaseAuthentificationRepository {
    override suspend fun authentification(email: String, password: String) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("AUTHENTIFICATION","Вы вошли в аккаунт")
            } else {
                Log.d("AUTHENTIFICATION","Неверный логин или пароль")
            }
        }
    }
}