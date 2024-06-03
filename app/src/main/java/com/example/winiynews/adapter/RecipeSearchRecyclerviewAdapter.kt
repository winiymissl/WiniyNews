package com.example.winiynews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.winiynews.R
import com.example.winiynews.bean.RecipeBean.Ingredient
import com.example.winiynews.databinding.ItemRecyclerviewRecipeSearchBinding

/**
 * @Author winiymissl
 * @Date 2024-06-03 14:32
 * @Version 1.0
 */
class RecipeSearchRecyclerviewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list: MutableList<ItemSearchData> = mutableListOf()
    fun submitList(data: List<ItemSearchData>) {
//        for (item in data) {
//            list.add(item)
//            notifyItemInserted(list.indexOf(item))
//        }
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerviewRecipeSearchBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_recipe_search, parent, false)
        )
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).bindTo(list[position], position)
    }

    class SearchViewHolder(val binding: ItemRecyclerviewRecipeSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: ItemSearchData, position: Int) {
            Glide.with(binding.root).load(item.cover).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(binding.imageViewFood)
//            binding.imageViewFood.setImageURI(Uri.parse(item.cover))
//            binding.chipRecipeTime.text = item.ingredient.toString()
            binding.textViewRecipeName.text = item.name
        }
    }
}

data class ItemSearchData(
    val cover: String,
    val desc: String,
    val id: Int,
    val ingredient: List<Ingredient>,
    val name: String
)