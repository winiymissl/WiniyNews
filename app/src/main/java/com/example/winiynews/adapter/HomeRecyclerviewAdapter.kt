package com.example.winiynews.adapter

/**
 * @Author winiymissl
 * @Date 2024-05-31 18:52
 * @Version 1.0
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.R
import com.example.winiynews.databinding.ItemRecyclerviewHomeBinding

/**
 * @Author winiymissl
 * @Date 2024-05-30 16:29
 * @Version 1.0
 */
class HomeRecyclerviewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var list: List<ItemHome>

    companion object {
        object PokemonComparator : DiffUtil.ItemCallback<ItemHome>() {
            override fun areItemsTheSame(oldItem: ItemHome, newItem: ItemHome): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ItemHome, newItem: ItemHome): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerviewHomeBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_home, parent, false)
        )
        return LifeViewHolder(binding)
    }

    fun submitList(list: List<ItemHome>) {
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
        holder.bindTo(list[position], position)
    }

    class LifeViewHolder(var binding: ItemRecyclerviewHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: ItemHome, id: Int): Unit {
            ViewCompat.setTransitionName(binding.constraintHomeItem, "shared_element_container_$id")

            binding.imageViewHome.setImageResource(item.image)
            binding.textviewHome.setText(item.name)
        }
    }
}

data class ItemHome(val name: String, val image: Int)