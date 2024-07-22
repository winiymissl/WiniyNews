package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.StoryContract
import com.example.winiynews.mvp.model.StoryCategoryModel
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-07-22 16:20
 * @Version 1.0
 */
class StoryPresenter : BasePresenter<StoryContract.View>(), StoryContract.Presenter {
    private val model: StoryCategoryModel by lazy {
        StoryCategoryModel()
    }

    override fun requestStoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        model.getStoryCategory().subscribe({
            Logger.d(it.toString())
            mRootView?.apply {
                dismissLoading()
                setStoryData(it)
            }
        }, { throwable ->
            Logger.d(throwable)
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
            }
        })
    }
}