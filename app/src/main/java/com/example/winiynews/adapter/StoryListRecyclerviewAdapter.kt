package com.example.winiynews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.bean.StoryBean.SData
import com.example.winiynews.databinding.ItemRecyclerviewStoryListBinding

/**
 * @Author winiymissl
 * @Date 2024-07-29 16:50
 * @Version 1.0
 */
class StoryListRecyclerviewAdapter(val mListener: RRV_ItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mData: List<SData>
    fun submitData(mData: List<SData>) {
        this.mData = mData
    }

    fun getData(): List<SData> {
        return mData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerviewStoryListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val holder = StoryListViewHolder(binding)

        /**
         * 设置点击事件
         * */
        val rrvListener = RRV_Listener(mListener)
        holder.itemView.setOnLongClickListener(rrvListener)
        holder.itemView.setOnClickListener(rrvListener)
        return holder
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StoryListViewHolder).bindTo(mData[position])
        /**
         * 设置id
         * */
        holder.itemView.id = position
    }

    class StoryListViewHolder(private val binding: ItemRecyclerviewStoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: SData) {
            binding.apply {
                textViewLength.text = data.length.toString()
                textViewTitle.text = data.title
                textViewReadTime.text = data.readTime
                textViewType.text = data.type
            }
        }
    }
}