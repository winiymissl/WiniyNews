package com.example.winiynews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.R
import com.example.winiynews.databinding.ItemRecyclerivewRecipeCategoryBinding

/**
 * @Author winiymissl
 * @Date 2024-06-02 15:32
 * @Version 1.0
 */

class RecipeCategoryRecyclerviewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerivewRecipeCategoryBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerivew_recipe_category, parent, false)
        )
        return CategoryViewHolder(binding)
    }

    fun submitList(data: MutableList<String>) {
        for (item in data) {
            list.add(item)
            notifyItemInserted(list.indexOf(item))
        }
//        list.addAll(data)
//        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoryViewHolder).bindTo(list[position])
    }

    class CategoryViewHolder(val binding: ItemRecyclerivewRecipeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(msg: String) {
            binding.textViewCategory.text = msg
        }
    }
}