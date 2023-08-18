package com.novikov.blubb.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentAuthorizationBinding
import com.novikov.blubb.presentation.viewmodels.AuthentificationFragmentViewmodel
import kotlinx.coroutines.launch

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

        binding.buttonCreateAccount.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_authorizationFragment_to_createAccountFragment)
        }

        return binding.root
    }


}