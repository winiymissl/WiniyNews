package com.example.winiynews.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author winiymissl
 * @Date 2024-06-01 14:05
 * @Version 1.0
 */
class MyOnItemTouchListener(
    context: Context?, private val rv: RecyclerView, private val mListener: OnItemClickListener?
) : RecyclerView.OnItemTouchListener {
    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)

        fun onLongItemClick(view: View?, position: Int)
    }

    var mGestureDetector: GestureDetector

    init {
        mGestureDetector = GestureDetector(context, ItemTouchHelperGestureListener())
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return mGestureDetector.onTouchEvent(e)
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        mGestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    private inner class ItemTouchHelperGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val childView = rv.findChildViewUnder(e.x, e.y)
            if (childView != null && mListener != null) {
                mListener.onItemClick(childView, rv.getChildAdapterPosition(childView))
                return true
            }
            return false
        }

        override fun onLongPress(e: MotionEvent) {
            val childView = rv.findChildViewUnder(e.x, e.y)
            if (childView != null && mListener != null) {
                mListener.onLongItemClick(childView, rv.getChildAdapterPosition(childView))
            }
        }
    }
}