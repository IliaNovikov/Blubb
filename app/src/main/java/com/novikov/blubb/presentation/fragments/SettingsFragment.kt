package com.novikov.blubb.presentation.fragments

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap.Config
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.novikov.blubb.R
import com.novikov.blubb.databinding.FragmentSettingsBinding
import java.util.Locale

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        setCurrentLocale()

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
}