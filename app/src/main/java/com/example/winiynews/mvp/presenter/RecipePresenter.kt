package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.RecipeContract
import com.example.winiynews.mvp.model.RecipeModel
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-06-02 14:58
 * @Version 1.0
 */
class RecipePresenter : BasePresenter<RecipeContract.View>(), RecipeContract.Presenter {
    private val model: RecipeModel by lazy { RecipeModel() }

    override fun requestRecipeCategoryData(categoryId: String) {
        checkViewAttached()
        mRootView?.showLoading()
        model.getRecipeCategory(categoryId).subscribe({
            mRootView?.apply {
                setRecipeCategoryData(it)
            }
        }, {
            Logger.d(it)
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
            }
        })
    }

    override fun requestSearchRecipeData(keyword: String, page: String) {
        checkViewAttached()
        mRootView?.showRecyclerviewLoading()
        model.searchRecipe(keyword, page).subscribe({
            mRootView?.apply {
                Logger.d(it)
                setSearchRecipeData(it)
            }
        }, {
            Logger.d(it)
            mRootView?.apply {
                dismissLoading()
                showRecyclerviewError(
                    ExceptionHandle.handleException(it),
                    ExceptionHandle.errorCode
                )
            }
        })
    }
}