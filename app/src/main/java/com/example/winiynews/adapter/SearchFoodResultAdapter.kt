package com.example.winiynews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.R
import com.example.winiynews.databinding.ItemRecyclerviewSearchFoodBinding

/**
 * @Author winiymissl
 * @Date 2024-06-01 18:35
 * @Version 1.0
 */
class SearchFoodResultAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: MutableList<ItemSearchResult> = mutableListOf()

    companion object {
        object Comparator : DiffUtil.ItemCallback<ItemSearchResult>() {
            override fun areItemsTheSame(
                oldItem: ItemSearchResult, newItem: ItemSearchResult
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: ItemSearchResult, newItem: ItemSearchResult
            ): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerviewSearchFoodBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_search_food, parent, false)
        )
        return LifeViewHolder(binding)
    }

    fun submitList(list: MutableList<ItemSearchResult>) {
        this.list = list
    }

    fun submitItem(item: ItemSearchResult) {
        list.add(item)
        notifyItemInserted(itemCount)
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

    class LifeViewHolder(var binding: ItemRecyclerviewSearchFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: ItemSearchResult, id: Int): Unit {
//            ViewCompat.setTransitionName(binding.constraintHomeItem, "shared_element_container_$id")
            binding.textViewCaloryData.setText(item.calory + "  calory")
            binding.textViewHealthLevelData.setText(item.healthLevel.toString() + "  level")
            binding.textViewName.setText(item.name)
        }
    }
}

data class ItemSearchResult(
    val foodId: String, val name: String, val healthLevel: Int, val calory: String
)