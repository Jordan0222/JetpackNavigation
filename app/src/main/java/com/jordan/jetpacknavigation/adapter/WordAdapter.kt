package com.jordan.jetpacknavigation.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jordan.jetpacknavigation.databinding.WordItemBinding
import com.jordan.jetpacknavigation.domain.model.WordItem
import com.jordan.jetpacknavigation.presentation.word_screen.WordFragment
import javax.inject.Inject

class WordAdapter @Inject constructor(
    private val context: Context,
): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    inner class WordViewHolder(val binding: WordItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<WordItem>() {

        override fun areItemsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
            return oldItem.word == newItem.word
        }

        override fun areContentsTheSame(oldItem: WordItem, newItem: WordItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<WordItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            with(items[position]) {
                binding.button.text = item.word
                binding.button.setOnClickListener {
                    val queryUrl: Uri = Uri.parse("${WordFragment.SEARCH_PREFIX}${item.word}")
                    val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(context, intent, null)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}