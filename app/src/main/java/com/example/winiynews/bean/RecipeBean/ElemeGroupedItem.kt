package com.example.winiynews.bean.RecipeBean

import com.kunminx.linkage.bean.BaseGroupedItem

/**
 * @Author winiymissl
 * @Date 2024-07-31 10:44
 * @Version 1.0
 */
class ElemeGroupedItem : BaseGroupedItem<ElemeGroupedItem.ItemInfo> {

    constructor(isHeader: Boolean, header: String) : super(isHeader, header) {

    }

    constructor(infoItem: ItemInfo) : super(infoItem) {

    }

    class ItemInfo : BaseGroupedItem.ItemInfo {

        private var _imageUrl: String = ""

        private var _content: String

        fun getContent(): String {
            return _content
        }

        fun getImageUrl(): String {
            return _imageUrl
        }

        override fun toString(): String {
            return "ItemInfo(_imageUrl='$_imageUrl', _content='$_content')"
        }

        constructor(title: String, group: String, content: String) : super(title, group) {
            this._content = content
        }

        constructor (
            title: String, group: String, content: String, imgUrl: String
        ) : super(title, group) {
            this._content = content
            this._imageUrl = imgUrl
        }

    }

}