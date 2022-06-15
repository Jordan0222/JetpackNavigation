package com.jordan.jetpacknavigation.presentation.word_screen

import android.os.Bundle
import android.view.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.jetpacknavigation.R
import com.jordan.jetpacknavigation.adapter.WordAdapter
import com.jordan.jetpacknavigation.databinding.FragmentWordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

        binding.composeTopBar.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue)
                ) {
                    IconButton(
                        onClick = {
                            wordViewModel.layoutManagerChange()
                            setUpRecyclerView()
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (wordViewModel.isLinearLayoutManager.value) R.drawable.ic_linear_layout else R.drawable.ic_grid_layout
                            ),
                            contentDescription = "layout Button"
                        )
                    }
                }
            }
        }

        setUpRecyclerView()
        subscribeToObservers()

        return binding.root
    }

    private fun setUpRecyclerView() = binding.recyclerView.apply {
        adapter = wordAdapter
        if (wordViewModel.isLinearLayoutManager.value) {
            layoutManager = LinearLayoutManager(requireContext())
        } else {
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
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