package com.jordan.jetpacknavigation.presentation.word_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.jetpacknavigation.adapter.WordAdapter
import com.jordan.jetpacknavigation.databinding.FragmentWordBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WordFragment : Fragment() {

    private lateinit var wordViewModel: WordViewModel

    private var _binding: FragmentWordBinding? = null
    private val binding: FragmentWordBinding
        get() = _binding!!

    @Inject
    lateinit var wordAdapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordBinding.inflate(inflater, container, false)

        wordViewModel = ViewModelProvider(requireActivity())[WordViewModel::class.java]

        setUpRecyclerView()
        subscribeToObservers()

        return binding.root
    }

    private fun setUpRecyclerView() = binding.recyclerView.apply {
        adapter = wordAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        wordViewModel.wordItems.observe(viewLifecycleOwner) {
            wordAdapter.items = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
}