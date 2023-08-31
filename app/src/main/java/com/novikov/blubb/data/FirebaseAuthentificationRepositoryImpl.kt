package com.novikov.blubb.data

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.repositories.FirebaseAuthentificationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class FirebaseAuthentificationRepositoryImpl():FirebaseAuthentificationRepository {
    override suspend fun authentification(email: String, password: String): Boolean {
        val auth = Firebase.auth
        var isLogin = false

//         auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//            isLogin = it.isSuccessful
//        }

        try{
            isLogin = auth.signInWithEmailAndPassword(email, password).await().user != null
        }
        catch (ex:Exception){
            isLogin = false
        }

        Log.i("authrepo", isLogin.toString())
        return isLogin
    }

    override suspend fun logOut() {
        val auth = Firebase.auth
        auth.signOut()
    }


}