package com.example.winiynews.bean.RecipeBean

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.winiynews.R
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder
import com.kunminx.linkage.bean.BaseGroupedItem
import com.kunminx.linkage.contract.ILinkageSecondaryAdapterConfig


/**
 * @Author winiymissl
 * @Date 2024-07-31 10:44
 * @Version 1.0
 */
class ElemeSecondaryAdapterConfig : ILinkageSecondaryAdapterConfig<ElemeGroupedItem.ItemInfo> {
    private var mContext: Context? = null
    override fun setContext(p0: Context?) {
        this.mContext = p0
    }

    override fun getLinearLayoutId(): Int {
        return R.layout.item_recyclerview_recipe_detail
    }

    override fun getHeaderLayoutId(): Int {
        return com.kunminx.linkage.R.layout.default_adapter_linkage_secondary_header
    }

    override fun getHeaderTextViewId(): Int {
        return com.kunminx.linkage.R.id.secondary_header
    }

    override fun getFooterLayoutId(): Int {
        return R.layout.footer
    }

    override fun getGridLayoutId(): Int {
        return 0
    }

    override fun onBindHeaderViewHolder(
        p0: LinkageSecondaryHeaderViewHolder?, p1: BaseGroupedItem<ElemeGroupedItem.ItemInfo>?
    ) {
        (p0?.getView(com.kunminx.linkage.R.id.secondary_header) as TextView).text = p1?.header
    }

    override fun onBindViewHolder(
        p0: LinkageSecondaryViewHolder?, p1: BaseGroupedItem<ElemeGroupedItem.ItemInfo>?
    ) {
        (p0?.getView(R.id.textView) as TextView).text = p1?.info?.getContent()
        val view = p0.getView(R.id.item_image) as ImageView
        Glide.with(mContext!!).load(p1?.info?.getImageUrl()).into(view)
    }
}