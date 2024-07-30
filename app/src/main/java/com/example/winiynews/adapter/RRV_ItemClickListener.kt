package com.example.winiynews.adapter

import android.view.View

/**
 * @Author winiymissl
 * @Date 2024-07-29 16:53
 * @Version 1.0
 */
interface RRV_ItemClickListener {
    fun onItemClick(view: View?, position: Int)
    fun onLongItemClick(view: View?, position: Int): Boolean
}