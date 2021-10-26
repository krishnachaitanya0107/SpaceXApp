package com.example.space_xapp.ui.ship

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.space_xapp.R
import com.example.space_xapp.databinding.FragmentShipBinding
import com.example.space_xapp.ui.crew.CrewViewModel
import com.example.space_xapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShipFragment : Fragment(R.layout.fragment_ship) {

    private var _binding:FragmentShipBinding?=null
    private val binding:FragmentShipBinding get() = _binding!!

    private val viewModel: ShipViewModel by viewModels()

    companion object {
        fun getInstance()=ShipFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding= FragmentShipBinding.bind(view)

        val shipAdapter=ShipAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = shipAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.ships.observe(viewLifecycleOwner){ result->

                shipAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                errorMessage.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                errorMessage.text= result.error?.localizedMessage ?: ""
            }
        }

        setHasOptionsMenu(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}