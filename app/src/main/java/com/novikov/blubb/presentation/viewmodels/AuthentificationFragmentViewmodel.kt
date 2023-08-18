package com.novikov.blubb.presentation.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.R
import com.novikov.blubb.domain.usecases.AuthentificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthentificationFragmentViewmodel @Inject constructor(
    private val authentificationUseCase: AuthentificationUseCase
) : ViewModel() {

    var isLoginLiveData: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun authentification(email: String, password: String){
       isLoginLiveData.value = authentificationUseCase.execute(email, password)
        Log.i("authvm", isLoginLiveData.value.toString())

     //       TODO: Сделать во вьюмодели метод который возвращает состояние пользователя
//        auth = Firebase.auth
//        auth.signInWithEmailAndPassword(emailLiveMutable.value!!, passwordLiveMutable.value!!).addOnCompleteListener {
//            if (it.isSuccessful) {
//                Snackbar.make(
//                    requireContext(),
//                    "Вы вошли в аккаунт",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//                requireActivity()
//                    .findNavController(R.id.nav_host_fragment)
//                    .navigate(R.id.action_authorizationFragment_to_mainFragment)
//            } else {
//                Snackbar.make(
//                    view,
//                    resources.getText(R.string.authorization_error),
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            }
//        }
    }

}

