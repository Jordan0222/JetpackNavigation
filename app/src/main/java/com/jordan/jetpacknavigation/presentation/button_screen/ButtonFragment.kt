package com.jordan.jetpacknavigation.presentation.button_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.databinding.FragmentButtonBinding

class ButtonFragment : Fragment(R.layout.fragment_button) {


    private var _binding: FragmentButtonBinding? = null
    private val binding: FragmentButtonBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentButtonBinding.inflate(inflater, container, false)

        return binding.root
    }


}