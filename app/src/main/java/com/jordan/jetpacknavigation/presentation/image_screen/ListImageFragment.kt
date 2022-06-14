package com.jordan.jetpacknavigation.presentation.image_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.jetpacknavigation.MainViewModel
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.adapter.ItemAdapter
import com.jordan.jetpacknavigation.databinding.FragmentListImageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListImageFragment : Fragment(R.layout.fragment_list_image) {

    private lateinit var listImageViewModel: ListImageViewModel

    private var _binding: FragmentListImageBinding? = null
    private val binding: FragmentListImageBinding
        get() = _binding!!

    @Inject
    lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListImageBinding.inflate(inflater, container, false)

        listImageViewModel = ViewModelProvider(requireActivity())[ListImageViewModel::class.java]

        setUpRecyclerView()
        subscribeToObservers()

        return binding.root
    }

    private fun setUpRecyclerView() = binding.recyclerView.apply {
        adapter = itemAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        listImageViewModel.listItems.observe(viewLifecycleOwner) {
            itemAdapter.items = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}