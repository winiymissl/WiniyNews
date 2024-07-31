package com.example.winiynews.bean.RecipeBean

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig


/**
 * @Author winiymissl
 * @Date 2024-07-31 10:31
 * @Version 1.0
 */
class ElemePrimaryAdapterConfig : ILinkagePrimaryAdapterConfig {
    var mContext: Context? = null
    override fun setContext(p0: Context?) {
        this.mContext = p0
    }

    override fun getLayoutId(): Int {
        return com.kunminx.linkage.R.layout.default_adapter_linkage_primary
    }

    override fun getGroupTitleViewId(): Int {
        return com.kunminx.linkage.R.id.tv_group
    }

    override fun getRootViewId(): Int {
        return com.kunminx.linkage.R.id.layout_group
    }

    override fun onBindViewHolder(p0: LinkagePrimaryViewHolder?, p1: Boolean, p2: String?) {
        val tvTitle = p0?.groupTitle as TextView
        tvTitle.apply {
            text = p2
            setBackgroundColor(
                mContext!!.resources.getColor(
                    if (p1) com.kunminx.linkage.R.color.colorPurple else com.kunminx.linkage.R.color.colorWhite
                )
            )
            setTextColor(
                ContextCompat.getColor(
                    mContext!!,
                    if (p1) com.kunminx.linkage.R.color.colorWhite else com.kunminx.linkage.R.color.colorGray
                )
            )
        }
    }
}