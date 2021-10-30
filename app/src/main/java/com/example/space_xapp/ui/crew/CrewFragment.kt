package com.example.space_xapp.ui.crew

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.space_xapp.R
import com.example.space_xapp.data.Crew
import com.example.space_xapp.databinding.FragmentCrewBinding
import com.example.space_xapp.ui.BaseFragmentDirections
import com.example.space_xapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrewFragment : Fragment(R.layout.fragment_crew), CrewAdapter.OnItemClickListener {

    private var _binding: FragmentCrewBinding? = null
    private val binding: FragmentCrewBinding get() = _binding!!

    private val viewModel: CrewViewModel by viewModels()

    companion object {
        fun getInstance() = CrewFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCrewBinding.bind(view)

        val crewAdapter = CrewAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = crewAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.crew.observe(viewLifecycleOwner) { result ->
                crewAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                errorMessage.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                errorMessage.text = result.error?.localizedMessage ?: ""
            }

        }

        setHasOptionsMenu(false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(crew: Crew) {
        val action = BaseFragmentDirections.actionBaseFragmentToDetailsFragment(crew)
        findNavController().navigate(action)
    }

}