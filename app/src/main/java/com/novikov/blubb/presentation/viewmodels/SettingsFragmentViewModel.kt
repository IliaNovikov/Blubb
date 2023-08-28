package com.novikov.blubb.presentation.viewmodels

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsFragmentViewModel @Inject constructor(private val app: Application) : AndroidViewModel(app){

    var userImageLiveData: MutableLiveData<Bitmap> = MutableLiveData()
    var userNicknameLiveData: MutableLiveData<String> = MutableLiveData()
    var userEmailLiveData: MutableLiveData<String> = MutableLiveData()
    var userPasswordLiveData: MutableLiveData<String> = MutableLiveData()

}