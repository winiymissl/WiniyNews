package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.RecipeDetailContract
import com.example.winiynews.mvp.model.RecipeDetailModel
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-07-30 21:31
 * @Version 1.0
 */
class RecipeDetailPresenter : BasePresenter<RecipeDetailContract.View>(),
    RecipeDetailContract.Presenter {
    private val model by lazy {
        RecipeDetailModel()
    }
    override fun requestRecipeDetailData(id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        model.getRecipeDetailData(id).subscribe({
            mRootView?.apply {
                dismissLoading()
                setRecipeDetailData(it)
            }
        }, {
            Logger.d(it)
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
            }
        })
    }
}