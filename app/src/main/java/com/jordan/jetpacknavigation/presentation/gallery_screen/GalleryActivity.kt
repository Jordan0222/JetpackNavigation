package com.jordan.jetpacknavigation.presentation.gallery_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.jordan.jetpacknavigation.adapter.GalleryAdapter
import com.jordan.jetpacknavigation.databinding.ActivityGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val galleryViewPager = binding.galleryVp

        val adapter = GalleryAdapter(this)
        galleryViewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, galleryViewPager) { tab, position ->
            tab.text = getTitle(position)
        }.attach()
    }

    private fun getTitle(position: Int): String? {
        return when (position) {
            0 -> "Cloud"
            1 -> "Mountain"
            2 -> "Sunshine"
            else -> null
        }
    }
}