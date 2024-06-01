package com.example.winiynews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.R
import com.example.winiynews.databinding.ItemRecyclerviewLifeBinding

/**
 * @Author winiymissl
 * @Date 2024-05-30 16:29
 * @Version 1.0
 */
class LifeRecyclerviewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var list: List<ItemLife>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerviewLifeBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_life, parent, false)
        )
        return LifeViewHolder(binding)
    }

    fun submitList(list: List<ItemLife>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        if (list == null) {
            return 0
        }
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var holder = holder as LifeViewHolder
        holder.bindTo(list[position])
    }

    class LifeViewHolder(var binding: ItemRecyclerviewLifeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: ItemLife): Unit {
            binding.itemRecyclerviewImage.setImageResource(item.image)
            binding.itemRecyclerviewTitle.setText(item.name)
        }
    }
}

data class ItemLife(val name: String, val image: Int)