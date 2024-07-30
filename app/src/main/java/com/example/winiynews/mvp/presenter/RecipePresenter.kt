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
                dismissLoading()
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
        if (page.toInt() == 1) {
        mRootView?.showRecyclerviewLoading()
        model.searchRecipe(keyword, page).subscribe({
            mRootView?.apply {
                dismissLoading()
                if (it.data.totalCount != 0) {
                    setSearchRecipeData(it)
                } else {
                    dismissLoading()
                    showRecyclerviewError(
                        "未找到", ExceptionHandle.errorCode
                    )
                }
            }
        }, {
            mRootView?.apply {
                dismissLoading()
                showRecyclerviewError(
                    ExceptionHandle.handleException(it), ExceptionHandle.errorCode
                )
            }
        })
        } else {
            //loadMore不需要显示加载动画
            model.searchRecipe(keyword, page).subscribe({
                mRootView?.apply {
                    setSearchRecipeDataMore(it)
                }
            }, {
                mRootView?.apply {
                    showRecyclerviewError(
                        ExceptionHandle.handleException(it), ExceptionHandle.errorCode
                    )
                }
            })
        }
    }
}