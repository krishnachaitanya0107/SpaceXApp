package com.example.space_xapp.ui.info

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.space_xapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment(R.layout.fragment_info) {

    private val viewModel:InfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.info.observe(viewLifecycleOwner){ result->
            Log.d("companyInfo",result.data.toString())
        }
    }

}