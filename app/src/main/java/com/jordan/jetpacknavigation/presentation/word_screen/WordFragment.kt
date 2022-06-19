package com.jordan.jetpacknavigation.presentation.word_screen

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordan.jetpacknavigation.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordBinding.inflate(inflater, container, false)

        wordViewModel = ViewModelProvider(requireActivity())[WordViewModel::class.java]

        binding.toolbar.setOnMenuItemClickListener { item ->

            when (item?.itemId) {
                R.id.action_switch_layout -> {
                    wordViewModel.layoutManagerChange()
                    setUpRecyclerView()
                    setIcon(item)
                }
            }
            true
        }

        setUpRecyclerView()
        subscribeToObservers()

        return binding.root
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }*/

    private fun setUpRecyclerView() = binding.recyclerView.apply {
        adapter = wordAdapter
        layoutManager = if (wordViewModel.isLinearLayoutManager.value) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), 3)
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon = if (wordViewModel.isLinearLayoutManager.value)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                wordViewModel.layoutManagerChange()
                setUpRecyclerView()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }*/

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
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }
}