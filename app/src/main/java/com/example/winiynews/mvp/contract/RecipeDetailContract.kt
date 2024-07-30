package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.RecipeBean.RecipeDetailBean

/**
 * @Author winiymissl
 * @Date 2024-07-30 21:22
 * @Version 1.0
 */
class RecipeDetailContract {
    interface View : IBaseView {
        fun setRecipeDetailData(bean: RecipeDetailBean)
        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        fun requestRecipeDetailData(id: String)
    }
}