package com.jordan.jetpacknavigation.presentation.image_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jordan.jetpacknavigation.databinding.FragmentListImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListImageFragment : Fragment() {

    private lateinit var binding: FragmentListImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListImageBinding.bind(view)
    }
}