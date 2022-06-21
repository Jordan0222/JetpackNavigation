package com.jordan.jetpacknavigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jordan.jetpacknavigation.databinding.ItemViewPagerBinding
import com.jordan.jetpacknavigation.domain.model.Intro
import javax.inject.Inject

class IntroAdapter @Inject constructor(
    private val context: Context,
    private val introList: List<Intro>
): RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(private val binding: ItemViewPagerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(intro: Intro){
            binding.descTv.text= intro.desc?.let { context.resources.getString(it) }
            binding.titleTv.text= intro.title
            binding.iconIv.setImageResource(intro.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        return IntroViewHolder(
            ItemViewPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bindItem(introList[position])
    }

    override fun getItemCount(): Int {
        return introList.size
    }
}