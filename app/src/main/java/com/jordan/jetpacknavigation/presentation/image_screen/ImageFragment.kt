package com.jordan.jetpacknavigation.presentation.image_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding: FragmentImageBinding
        get() = _binding!!

    private val args: ImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageBinding.inflate(inflater, container, false)

        binding.itemTitle.text = args.imageText
        binding.itemImage.setImageResource(args.imageResource)

        binding.cardView.setOnClickListener {
            val action = ImageFragmentDirections.actionImageFragmentToFlowFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}