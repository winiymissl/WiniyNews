package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.mvp.contract.RecipeDetailContract

/**
 * @Author winiymissl
 * @Date 2024-07-30 21:31
 * @Version 1.0
 */
class RecipeDetailPresenter : BasePresenter<RecipeDetailContract.View>(),
    RecipeDetailContract.Presenter {
    override fun requestRecipeDetailData(id: String) {
        checkViewAttached()
        mRootView?.showLoading()
    }
}