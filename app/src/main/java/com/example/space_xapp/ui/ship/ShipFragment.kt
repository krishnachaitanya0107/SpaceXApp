package com.example.space_xapp.ui.ship

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.space_xapp.R
import com.example.space_xapp.data.Ship
import com.example.space_xapp.databinding.FragmentShipBinding
import com.example.space_xapp.ui.HomeFragmentDirections
import com.example.space_xapp.util.Constants
import com.example.space_xapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShipFragment : Fragment(R.layout.fragment_ship), ShipAdapter.OnItemClickListener {

    private var _binding: FragmentShipBinding? = null
    private val binding: FragmentShipBinding get() = _binding!!

    private val viewModel: ShipViewModel by viewModels()


    @Inject
    lateinit var sharedPref: SharedPreferences

    companion object {
        fun getInstance() = ShipFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentShipBinding.bind(view)

        val shipAdapter = ShipAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = shipAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            /*
            viewModel.lastUpdated =
                sharedPref.getLong(Constants.LAST_UPDATED, 0L)

            Log.d("lastUpdated",sharedPref.getLong(Constants.LAST_UPDATED, 0L).toString())

            viewModel.updateLastFetchedFunction =
                { currMillis-> sharedPref.edit().putLong(Constants.LAST_UPDATED, currMillis).apply() }

             */
            viewModel.ships.observe(viewLifecycleOwner) { result ->

                shipAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                errorMessage.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                errorMessage.text = result.error?.localizedMessage ?: ""
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(ship: Ship) {
        val action = HomeFragmentDirections.actionBaseFragmentToShipDetailsFragment(ship, ship.name)
        findNavController().navigate(action)
    }

}