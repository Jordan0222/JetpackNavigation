package com.jordan.jetpacknavigation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jordan.jetpacknavigation.presentation.gallery_screen.GalleryFragment

class GalleryAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = GalleryFragment.newInstance(position)
}