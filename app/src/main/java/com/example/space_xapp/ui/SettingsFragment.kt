package com.example.space_xapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.space_xapp.R
import com.example.space_xapp.databinding.FragmentSettingsBinding
import com.example.space_xapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        binding.apply {

            darkModeSwitch.isChecked = sharedPref.getBoolean(Constants.NIGHT_MODE, false)

            darkModeSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                sharedPref.edit().putBoolean(Constants.NIGHT_MODE, isChecked).apply()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}