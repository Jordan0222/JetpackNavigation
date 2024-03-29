package com.jordan.jetpacknavigation.presentation.home_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.databinding.FragmentHomeBinding
import com.jordan.jetpacknavigation.presentation.pager_screen.PagerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val TAG = "HomeFragment Lifecycle"

    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.calculateButton.setOnClickListener {

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            val isChecked = binding.roundUpSwitch.isChecked

            mainViewModel.calculateTip(
                binding.costOfServiceEditText.text.toString(),
                tipPercentage,
                isChecked
            )
        }

        binding.toPagerActivityButton.setOnClickListener {
            val intent = Intent(context, PagerActivity::class.java)
            startActivity(intent)
        }

        subscribeToObservables()
        return binding.root
    }

    private fun subscribeToObservables() {
        mainViewModel.tipResult.observe(viewLifecycleOwner) {
            binding.tipResult.text = it
        }
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: ")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")
        super.onDestroyView()
        _binding = null
    }
}