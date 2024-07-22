package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.RecipeBean.RecipeCategoryBean
import com.example.winiynews.bean.RecipeBean.SearchRecipeBean

/**
 * @Author winiymissl
 * @Date 2024-06-02 14:44
 * @Version 1.0
 */
interface RecipeContract {
    interface View : IBaseView {
        fun showRecyclerviewLoading()

        fun showRecyclerviewError(msg: String, errorCode: Int)

        fun setRecipeCategoryData(data: RecipeCategoryBean)
        fun setSearchRecipeData(data: SearchRecipeBean)
        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        fun requestRecipeCategoryData(categoryId: String)
        fun requestSearchRecipeData(keyword: String, page: String)
    }
}