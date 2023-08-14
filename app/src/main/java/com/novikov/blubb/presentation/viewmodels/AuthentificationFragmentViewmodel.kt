package com.novikov.blubb.presentation.viewmodels

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novikov.blubb.R

class AuthentificationFragmentViewmodel : ViewModel() {

    private val emailLiveMutable: MutableLiveData<String> = MutableLiveData()
    private val passwordLiveMutable: MutableLiveData<String> = MutableLiveData()


}

