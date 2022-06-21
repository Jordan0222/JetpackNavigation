package com.jordan.jetpacknavigation.presentation.pager_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.jordan.jetpacknavigation.adapter.IntroAdapter
import com.jordan.jetpacknavigation.databinding.ActivityPagerBinding
import com.jordan.jetpacknavigation.domain.Datasource
import com.jordan.jetpacknavigation.presentation.gallery_screen.GalleryActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagerBinding

    private var viewPager2: ViewPager2? = null

    private val pager2Callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (position == Datasource().loadIntroList().size - 1) {
                binding.controllerBtn.text = "Finish"
                binding.controllerBtn.setOnClickListener {
                    val intent = Intent(this@PagerActivity, GalleryActivity::class.java)
                    startActivity(intent)
                }
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

        setupViewPager()
    }

    private fun setupViewPager() {
        viewPager2 = binding.viewPager
        viewPager2?.adapter = IntroAdapter(this, Datasource().loadIntroList())
        viewPager2?.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2?.unregisterOnPageChangeCallback(pager2Callback)
    }
}