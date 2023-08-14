package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(layoutInflater)
        auth = Firebase.auth

        val login = binding.editTextNickname.text.toString()
        val password = binding.editTextPassword.text.toString()

        auth.signInWithEmailAndPassword(login, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    requireContext(),
                  "Вы вошли в аккаунт",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                  requireContext(),
                    resources.getText(R.string.authorization_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }



        return binding.root
    }


}