package com.jordan.jetpacknavigation.presentation.home_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
    }
}