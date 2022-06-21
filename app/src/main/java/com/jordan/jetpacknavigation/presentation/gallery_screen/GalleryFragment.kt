package com.jordan.jetpacknavigation.presentation.gallery_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.adapter.IntroAdapter
import com.jordan.jetpacknavigation.databinding.FragmentGalleryBinding
import com.jordan.jetpacknavigation.domain.Datasource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pos = arguments?.getInt(POSITION_ARG)
        val viewPager = binding.galleryVp

        pos?.let {
            when (pos) {
                0 -> viewPager.adapter = IntroAdapter(requireContext(), Datasource().loadCloud())
                1 -> viewPager.adapter = IntroAdapter(requireContext(), Datasource().loadMountain())
                2 -> viewPager.adapter = IntroAdapter(requireContext(), Datasource().loadSunshine())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var POSITION_ARG = "position_arg"
        @JvmStatic
        fun newInstance(position: Int) = GalleryFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position)
            }
        }
    }
}