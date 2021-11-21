package com.example.space_xapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.space_xapp.databinding.FragmentHomeBinding
import com.example.space_xapp.util.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    lateinit var pagerAdapter: ViewPagerAdapter
    private var mediator: TabLayoutMediator? = null
    val tabNames = listOf("Crew", "Ships")

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewpager()
    }


    private fun setUpViewpager() {

        pagerAdapter =
            ViewPagerAdapter(fragmentManager = childFragmentManager, lifecycle = lifecycle)

        binding.viewPager2.isUserInputEnabled = true

        binding.viewPager2.adapter = pagerAdapter
        binding.viewPager2.registerOnPageChangeCallback(pageChangeCallback)

        if (mediator != null)
            mediator!!.detach()
        pagerAdapter.removeAllFragments()
        pagerAdapter.addFragments()
        binding.viewPager2.offscreenPageLimit = pagerAdapter.itemCount

        val tabs = mutableListOf<String>().apply {
            addAll(tabNames)
        }

        val strategy =
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = tabs[position]
            }

        mediator = TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager2,
            strategy
        )

        mediator?.attach()
    }

    override fun onStop() {
        binding.viewPager2.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onStop()
    }

}