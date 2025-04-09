package com.example.mangami.features.auth.viewpager.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mangami.R
import com.example.mangami.databinding.FragmentViewPagerBinding
import com.example.mangami.features.auth.viewpager.presentation.adapter.ViewPagerAdapter

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = arrayOf(
            ExploreTheWorldEasilyFragment(),
            MakeConnectsWithTravelloFragment(),
            ReachTheUnknownFragment()
        )
        val adapter = ViewPagerAdapter(fragmentManager = childFragmentManager , lifecycle = lifecycle, fragments = fragment)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.viewPager)
        binding.imageNext.setOnClickListener {
            if (binding.viewPager.currentItem  != 2) {
                binding.viewPager.currentItem++
            }else{
                findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}