package com.novikov.blubb.presentation.viewmodels

import android.app.Application
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.novikov.blubb.R
import com.novikov.blubb.domain.models.User
import com.novikov.blubb.domain.usecases.CreateUserUseCase
import com.novikov.blubb.domain.usecases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountFragmentViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    var emailLiveData: MutableLiveData<String> = MutableLiveData()
    var nicknameLiveData: MutableLiveData<String> = MutableLiveData()
    var passwordLiveData: MutableLiveData<String> = MutableLiveData()

    var isCorrect : MutableLiveData<Boolean> = MutableLiveData()



    suspend fun createUser(){
        val user = User(
            nickname = nicknameLiveData.value.toString(),
            password = passwordLiveData.value.toString(),
            email = emailLiveData.value.toString(),
            id = "0",
            userImage = BitmapFactory.decodeResource(app.resources, R.drawable.blubb_default_avatar))

        createUserUseCase.execute(user)
    }
}