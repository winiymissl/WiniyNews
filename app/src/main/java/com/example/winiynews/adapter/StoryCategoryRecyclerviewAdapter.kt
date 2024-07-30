package com.example.winiynews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.bean.StoryBean.Data
import com.example.winiynews.databinding.ItemRecyclerivewRecipeCategoryBinding

/**
 * @Author winiymissl
 * @Date 2024-07-22 16:40
 * @Version 1.0
 */
class StoryCategoryRecyclerviewAdapter(val mListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mData: List<Data>
    fun submitList(data: List<Data>) {
        this.mData = data
    }

    fun getList(): List<Data> {
        return mData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerivewRecipeCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        val holder = StoryViewHolder(binding)
        val listener = RV_Listener()
        holder.itemView.setOnClickListener(listener)
        holder.itemView.setOnLongClickListener(listener)
        return holder
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onLongItemClick(view: View?, position: Int): Boolean
    }

    inner class RV_Listener : View.OnClickListener, View.OnLongClickListener {
        override fun onClick(v: View?) {
            v?.let { mListener.onItemClick(v, v.id) }
        }

        override fun onLongClick(v: View?): Boolean {
            v?.let { mListener.onLongItemClick(v, v.id) }
            return true
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StoryViewHolder).bindTo(mData[position])
        holder.itemView.id = position
    }

    class StoryViewHolder(private val binding: ItemRecyclerivewRecipeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: Data) {
            binding.textViewCategory.text = data.name
        }
    }
}