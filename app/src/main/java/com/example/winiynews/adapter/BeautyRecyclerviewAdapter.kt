package com.example.winiynews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.winiynews.R
import com.example.winiynews.bean.beautyBean.Data
import com.example.winiynews.databinding.ItemBeautyBinding

/**
 * @Author winiymissl
 * @Date 2024-05-31 17:09
 * @Version 1.0
 */

class BeautyRecyclerviewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var list: List<Data>
    fun setData(list: List<Data>) {
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_beauty, parent, false)
        val binding = ItemBeautyBinding.bind(view)
        return BeautyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (list == null) {
            return 0
        }
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BeautyViewHolder) {
            holder.bindTo(list[position])
        }
    }

    class BeautyViewHolder(private val binding: ItemBeautyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: Data) {
            Glide.with(binding.root).load(data.imageUrl).into(binding.carouselImageView)
        }
    }
}