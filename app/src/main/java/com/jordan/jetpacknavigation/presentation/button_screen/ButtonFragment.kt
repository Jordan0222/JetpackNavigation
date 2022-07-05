package com.jordan.jetpacknavigation.presentation.button_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnAndroid -> showToast("This is Android")
                    R.id.btnFlutter -> showToast("This is Flutter")
                    R.id.btnWeb -> showToast("This is Web")
                }
            } else {
                if (toggleButtonGroup.checkedButtonId == View.NO_ID) {
                    showToast("Nothing Selected")
                }
            }
        }

        return binding.root
    }

    private fun showToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }
}