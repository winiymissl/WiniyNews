package com.example.winiynews.adapter

import android.view.View

/**
 * @Author winiymissl
 * @Date 2024-07-29 16:51
 * @Version 1.0
 */
class RRV_Listener(val mListener: RRV_ItemClickListener) : View.OnClickListener,
    View.OnLongClickListener {
    override fun onClick(v: View?) {
        v?.let { mListener.onItemClick(v, v.id) }
    }

    override fun onLongClick(v: View?): Boolean {
        v?.let { mListener.onLongItemClick(v, v.id) }
        return true
    }
}