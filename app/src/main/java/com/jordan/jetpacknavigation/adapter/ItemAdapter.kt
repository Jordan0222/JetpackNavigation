package com.jordan.jetpacknavigation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jordan.jetpacknavigation.databinding.ListItemBinding
import com.jordan.jetpacknavigation.domain.model.ListItem
import com.jordan.jetpacknavigation.presentation.image_list_screen.ListImageFragmentDirections
import javax.inject.Inject

class ItemAdapter @Inject constructor(
    private val context: Context,
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<ListItem>() {

        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.stringResourceId == newItem.stringResourceId
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<ListItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        val action = ListImageFragmentDirections.actionListImageFragmentToImageFragment(
            imageResource = item.imageResourceId,
            imageText = context.resources.getString(item.stringResourceId)
        )

        with(holder) {
            with(items[position]) {
                binding.itemTitle.text = context.resources.getString(item.stringResourceId)
                binding.itemImage.setImageResource(item.imageResourceId)

                binding.cardView.setOnClickListener {
                    holder.itemView.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}