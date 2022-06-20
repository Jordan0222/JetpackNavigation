package com.jordan.jetpacknavigation.presentation.pager_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.jordan.jetpacknavigation.adapter.IntroAdapter
import com.jordan.jetpacknavigation.databinding.ActivityPagerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagerBinding

    private lateinit var pagerViewModel: PagerViewModel

    private var viewPager2: ViewPager2? = null

    @Inject
    lateinit var introAdapter: IntroAdapter

    private val pager2Callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (position == introAdapter.items.size - 1) {
                binding.controllerBtn.text = "Finish"
            } else {
                binding.controllerBtn.text = "Next"
                binding.controllerBtn.setOnClickListener {
                    viewPager2?.currentItem = position + 1
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pagerViewModel = ViewModelProvider(this)[PagerViewModel::class.java]

        subscribeToObservers()
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPager2 = binding.viewPager
        viewPager2?.adapter = introAdapter
        viewPager2?.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2!!)
    }

    private fun subscribeToObservers() {
        pagerViewModel.introItems.observe(this) {
            introAdapter.items = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2?.unregisterOnPageChangeCallback(pager2Callback)
    }
}