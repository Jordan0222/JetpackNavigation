package com.jordan.jetpacknavigation.presentation.flow_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jordan.jetpacknavigation.databinding.FragmentFlowBinding
import com.jordan.jetpacknavigation.presentation.word_screen.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowFragment : Fragment() {

    private lateinit var flowViewModel: FlowViewModel

    private var _binding: FragmentFlowBinding? = null
    private val binding: FragmentFlowBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlowBinding.inflate(inflater, container, false)

        flowViewModel = ViewModelProvider(requireActivity())[FlowViewModel::class.java]

        binding.include.back.setOnClickListener {
            val action = FlowFragmentDirections.actionFlowFragmentToListImageFragment()
            findNavController().navigate(action)
        }

        binding.composeText.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Text(text = flowViewModel.numberString)
            }
        }

        return binding.root
    }
}