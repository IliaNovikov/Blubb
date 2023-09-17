package com.novikov.blubb.presentation.fragments

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentSettingsBinding
import com.novikov.blubb.presentation.dialogs.LogOutDialog
import com.novikov.blubb.presentation.dialogs.LogOutDialogCallback
import com.novikov.blubb.presentation.viewmodels.SettingsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.Locale

@AndroidEntryPoint
class SettingsFragment : Fragment(), LogOutDialogCallback {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsFragmentViewModel by viewModels()

    private lateinit var logOutListener: LogOutDialogCallback

    val GALLERY_REQUEST = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        logOutListener = this

        setCurrentLocale()

        Log.i("user", FirebaseAuth.getInstance().currentUser?.uid.toString())

        val loadingDialog = AlertDialog.Builder(requireContext(),
            androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog)
            .apply {
                setView(R.layout.dialog_loading)
            }.create()

        loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        loadingDialog.show()

        lifecycleScope.launch {
            viewModel.getUser()
        }.invokeOnCompletion {
            loadingDialog.dismiss()
        }

        viewModel.userEmailLiveData.observe(requireActivity(), Observer {
                binding.editTextEmailSettings.setText(it)
                Log.i("settings", it)
        })

        viewModel.userNicknameLiveData.observe(requireActivity(), Observer {
            binding.editTextNicknameSettings.setText(it)
            Log.i("settings", it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBackSettings.setOnClickListener {
            requireActivity()
                .findNavController(R.id.nav_host_fragment)
                .navigate(R.id.mainFragment)
        }

        binding.radioRussianLanguage.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked)
                setLocale("ru", "RU")
        }
        binding.radioEnglishLanguage.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked)
                setLocale("en", "EN")
        }

        binding.buttonLogOut.setOnClickListener {
            val dialog = LogOutDialog(logOutListener)
            dialog.show(childFragmentManager, "LogOutDialog")
        }

        binding.buttonChangeImage.setOnClickListener {
            val imageIntent = Intent(Intent.ACTION_PICK)
            imageIntent.type = "image/*"
            startActivityForResult(imageIntent, GALLERY_REQUEST)
        }

        binding.buttonSaveSettings?.setOnClickListener {

            if (checkFields()){

                val loadingDialog = AlertDialog.Builder(requireContext(),
                    androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog)
                    .apply {
                        setView(R.layout.dialog_loading)
                    }.create()

                loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                loadingDialog.show()

                Log.i("save", "begin")

                viewModel.userEmailLiveData.value = binding.editTextEmailSettings.text.toString()
                viewModel.userNicknameLiveData.value = binding.editTextNicknameSettings.text.toString()
                viewModel.userImageLiveData.value = (binding.imageViewUserAvatarSettings.drawable as BitmapDrawable).bitmap

                Log.i("save", "livedata")

                lifecycleScope.launch {
                    viewModel.saveUser()
                }.invokeOnCompletion {
                    loadingDialog.dismiss()
                }
                Log.i("save", "end")
            }
            else{
                Snackbar.make(view, getString(R.string.authorization_error), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST){
            if (resultCode == RESULT_OK){
                val image = data?.data
                val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, image)
                binding.imageViewUserAvatarSettings.setImageBitmap(bitmap)
//                val bitmap = (binding.imageViewUserAvatarSettings.drawable as BitmapDrawable).bitmap

            }
        }
    }

    private fun setLocale(languageCode: String, countryCode: String) {
        val locale = Locale(languageCode, countryCode)
        if (locale != Locale.getDefault()) {
            Locale.setDefault(locale)
            val resources = getResources()
            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, getResources().displayMetrics)
            requireActivity().recreate()
        }
    }
    private fun setCurrentLocale(){
        val resources:Resources = getResources()
        val config: Configuration =resources.configuration;

        if(Locale.getDefault().toLanguageTag().equals("en-GB") || Locale.getDefault().toLanguageTag().equals("en-EN"))
            binding.radioEnglishLanguage.isChecked = true
        if(Locale.getDefault().toLanguageTag().equals("ru-RU"))
            binding.radioRussianLanguage.isChecked = true
    }

    override fun onLogOutYesListener() {
        lifecycleScope.launch {
            viewModel.logOut()
        }
        requireActivity().
        findNavController(R.id.nav_host_fragment).
        navigate(R.id.authorizationFragment)
    }

    private fun checkFields(): Boolean{
        return (binding.editTextEmailSettings.text.toString().isNotEmpty()
                && binding.editTextNicknameSettings.text.toString().isNotEmpty())
    }
}