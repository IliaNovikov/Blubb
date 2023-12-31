package com.novikov.blubb.presentation.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentCreateAccountBinding
import com.novikov.blubb.presentation.dialogs.LoadingDialog
import com.novikov.blubb.presentation.viewmodels.CreateAccountFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding

    private lateinit var auth: FirebaseAuth

    private val viewModel: CreateAccountFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(layoutInflater)

        auth = Firebase.auth

        binding.buttonCreateAccountCreate.setOnClickListener { view ->

              if (checkFields()) {
                  val loadingDialog = AlertDialog.Builder(requireContext(),
                      androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog)
                      .apply {
                      setView(R.layout.dialog_loading)
                  }.create()

                  loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                  loadingDialog.show()

                  viewModel.emailLiveData.value = binding.editTextCreateAccountEmail.text.toString()
                  viewModel.nicknameLiveData.value =
                      binding.editTextCreateAccountNickname.text.toString()
                  viewModel.passwordLiveData.value =
                      binding.editTextCreateAccountPassword.text.toString()

                  lifecycleScope.launch {
                      viewModel.createUser()
                  }.invokeOnCompletion {

                      loadingDialog.dismiss()

                      requireActivity()
                          .findNavController(R.id.nav_host_fragment)
                          .navigate(R.id.action_createAccountFragment_to_mainFragment)
                  }
              }
        }

        binding.buttonCreateAccountAuthorization.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_createAccountFragment_to_authorizationFragment)
        }

        return binding.root
    }

    private fun checkFields(): Boolean {
        val email = binding.editTextCreateAccountEmail.text.toString()

        var isCorrect = true

        if (binding.editTextCreateAccountNickname.text.toString() == "") {
            binding.editTextCreateAccountNickname.error = "required"
            isCorrect = false
        }
        if (binding.editTextCreateAccountEmail.text.toString() == "") {
            binding.editTextCreateAccountEmail.error = "required"
            isCorrect = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextCreateAccountEmail.error = "incorrect"
            isCorrect = false
        }
        if (binding.editTextCreateAccountPassword.text.toString() == "") {
            binding.editTextCreateAccountPassword.error = "required"
            isCorrect = false
        }
        if (binding.editTextCreateAccountPassword.text.length < 6) {
            binding.editTextCreateAccountPassword.error = "required"
            isCorrect = false
        }
        if (binding.editTextCreateAccountRepeatPassword.text.toString() == "") {
            binding.editTextCreateAccountRepeatPassword.error = "required"
            isCorrect = false
        }
        if (binding.editTextCreateAccountPassword.text.toString() != binding.editTextCreateAccountRepeatPassword.text.toString()) {
            binding.editTextCreateAccountPassword.error = "passwords don`t match"
            isCorrect = false
        }
        return isCorrect
    }
}