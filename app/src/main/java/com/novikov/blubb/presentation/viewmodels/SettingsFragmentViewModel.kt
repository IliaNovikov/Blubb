package com.novikov.blubb.presentation.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.usecases.GetUserUseCase
import com.novikov.blubb.domain.usecases.LogOutUseCase
import com.novikov.blubb.domain.usecases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsFragmentViewModel @Inject constructor(private val app: Application,
                                                    private val getUserUseCase: GetUserUseCase,
                                                    private val logOutUseCase: LogOutUseCase,
                                                    private val saveUserUseCase: SaveUserUseCase) : AndroidViewModel(app){

    var userImageLiveData: MutableLiveData<Bitmap> = MutableLiveData()
    var userNicknameLiveData: MutableLiveData<String> = MutableLiveData()
    var userEmailLiveData: MutableLiveData<String> = MutableLiveData()
    var userPasswordLiveData: MutableLiveData<String> = MutableLiveData()
    private lateinit var user: User

    suspend fun getUser(){
        user = getUserUseCase.execute()
        Log.i("uservm", user.id)

        userNicknameLiveData.value = user.nickname
        userEmailLiveData.value = user.email
    }

    suspend fun saveUser(){

        val user2 = User(id = user.id,
            nickname = userNicknameLiveData.value.toString(),
            password = user.password,
            email = userEmailLiveData.value.toString(),
            userImage = userImageLiveData.value)

        Log.i("vm", "user2")

        saveUserUseCase.execute(user2)
    }

    suspend fun logOut(){
        logOutUseCase.execute()
    }


}