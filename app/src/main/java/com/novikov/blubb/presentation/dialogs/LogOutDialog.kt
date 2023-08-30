package com.novikov.blubb.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.novikov.blubb.R
import com.novikov.blubb.databinding.DialogLogOutBinding

class LogOutDialog: DialogFragment() {

    private val binding: DialogLogOutBinding by lazy {
        DialogLogOutBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonCancelLogOut.setOnClickListener {
            dismiss()
        }

        binding.buttonLogOutYes.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.authorizationFragment)
        }

    }

}