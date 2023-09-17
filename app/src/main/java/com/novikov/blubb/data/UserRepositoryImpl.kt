package com.novikov.blubb.data

import android.graphics.Bitmap
import android.util.Log
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.snapshots
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.novikov.blubb.R
import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.repositories.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class UserRepositoryImpl(): UserRepository {
    override suspend fun getUser(): User {

        if(FirebaseAuth.getInstance().currentUser != null)
            Log.i("userrepo123", FirebaseAuth.getInstance().currentUser?.uid.toString())
        else
            Log.i("userrepo", "null")

        Log.i("qwerty", "test")

        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val email = FirebaseAuth.getInstance().currentUser?.email.toString()
        val nickname = Firebase.database.reference
            .child("users").child(uid).child("nickname").get().await().value.toString()

//        Firebase.database.reference.child("users").child(uid).child("nickname").get().addOnSuccessListener {
//            nickname = it.value.toString()
//            Log.i("listener", "end")
//        }

        Log.i("nickname", nickname)

        Log.i("uid getUser", uid)
        Log.i("email getUser", email)

        val user = User(uid, nickname, email)
        return user

    }

    override suspend fun createUser(user: User) {
        val database = Firebase.database.reference
        val auth = Firebase.auth

        auth.createUserWithEmailAndPassword(user.email, user.password!!).await()

        user.id = auth.currentUser?.uid.toString()

        database.child("users").child(user.id).child("nickname").setValue(user.nickname)

    }

    override suspend fun saveUser(user: User) {
        val database = Firebase.database.reference
        val auth = Firebase.auth
        val store = Firebase.storage.reference



        user.id = auth.currentUser?.uid.toString()

        database.child("users").child(user.id).child("nickname").setValue(user.nickname)

        val imagesReference = store.child("userImages/" + user.id)

        val bitmap = user.userImage

        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 80, baos)
        val imageData = baos.toByteArray()

        imagesReference.putBytes(imageData).await()

    }
}