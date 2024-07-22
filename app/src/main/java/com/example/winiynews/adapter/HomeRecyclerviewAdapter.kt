package com.example.winiynews.adapter

/**
 * @Author winiymissl
 * @Date 2024-05-31 18:52
 * @Version 1.0
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.winiynews.R
import com.example.winiynews.databinding.ItemRecyclerviewHomeBinding

/**
 * @Author winiymissl
 * @Date 2024-05-30 16:29
 * @Version 1.0
 */
class HomeRecyclerviewAdapter(val mListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var list: List<ItemHome>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRecyclerviewHomeBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_home, parent, false)
        )
        val holder = LifeViewHolder(binding)
        val rvListener = RV_listener()
        holder.itemView.setOnLongClickListener(rvListener)
        holder.itemView.setOnClickListener(rvListener)
        return LifeViewHolder(binding)
    }

    inner class RV_listener : View.OnClickListener, View.OnLongClickListener {
        override fun onClick(v: View?) {
            v?.let { mListener.onItemClick(v, v.id) }
        }

        override fun onLongClick(v: View?): Boolean {
            v?.let {
                return mListener.onLongItemClick(v, v.id) ?: false
            }
            return false
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onLongItemClick(view: View?, position: Int): Boolean
    }
    fun submitList(list: List<ItemHome>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var holder = holder as LifeViewHolder
        holder.bindTo(list[position], position)
        holder.itemView.id = position
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