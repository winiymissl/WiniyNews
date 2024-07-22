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

class BeautyRecyclerviewAdapter(val mListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    private var list: List<Data> = mutableListOf()
    fun setData(list: List<Data>) {
        this.list = list
    }
    fun getList(): List<Data> {
        return list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_beauty, parent, false)
        val binding = ItemBeautyBinding.bind(view)
        val holder = BeautyViewHolder(binding)
        binding.root.setOnLongClickListener(RV_listener())
        binding.root.setOnClickListener(RV_listener())
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BeautyViewHolder) {
            holder.bindTo(list[position])
            holder.itemView.id = position
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onLongItemClick(view: View?, position: Int): Boolean
    }

    inner class RV_listener : View.OnClickListener, View.OnLongClickListener {
        override fun onClick(v: View?) {
            v?.let { mListener?.onItemClick(v, v.id) }
        }

        override fun onLongClick(v: View?): Boolean {
            v?.let {
                return mListener?.onLongItemClick(v, v.id) ?: false
            }
            return false
        }
    }

    class BeautyViewHolder(private val binding: ItemBeautyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: Data) {
            Glide.with(binding.root).load(data.imageUrl).into(binding.carouselImageView)
        }
    }
}