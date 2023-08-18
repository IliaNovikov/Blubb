package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentAuthorizationBinding
import com.novikov.blubb.presentation.viewmodels.AuthentificationFragmentViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    private val viewModel: AuthentificationFragmentViewmodel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(layoutInflater)

        binding.buttonLogin.setOnClickListener { view ->
            val email = binding.editTextNickname.text.toString()
            val password = binding.editTextPassword.text.toString()

            lifecycleScope.launch {
                viewModel.authentification(email, password)
            }

        }

        viewModel.isLoginLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                Snackbar.make(
                    requireView(),
                    "Вы вошли в аккаунт",
                    Snackbar.LENGTH_SHORT
                ).show()
                requireActivity()
                    .findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_authorizationFragment_to_mainFragment)
            } else {
                Snackbar.make(
                    requireView(),
                    resources.getText(R.string.authorization_error),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        binding.buttonCreateAccount.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_authorizationFragment_to_createAccountFragment)
        }

        return binding.root
    }


}