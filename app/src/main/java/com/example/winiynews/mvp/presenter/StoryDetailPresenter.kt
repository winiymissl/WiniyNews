package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.StoryDetailContract
import com.example.winiynews.mvp.model.StoryDetailModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * @Author winiymissl
 * @Date 2024-07-29 21:28
 * @Version 1.0
 */
class StoryDetailPresenter : BasePresenter<StoryDetailContract.View>(),
    StoryDetailContract.Presenter {
    private val model by lazy {
        StoryDetailModel()
    }

    override suspend fun requestStoryDetailData(storyId: String) {
        checkViewAttached()
        mRootView?.showLoading()
        model.getStoryDetailData(storyId).flowOn(Dispatchers.IO).catch {
            try {
                withContext(Dispatchers.Main) {
                    Logger.d(it)
                    mRootView?.dismissLoading()
                    mRootView?.showError(
                        ExceptionHandle.handleException(it), ExceptionHandle.errorCode
                    )
                }
            } catch (e: Exception) {
                Logger.d(e)
            }
        }.collect {
            try {
                mRootView?.dismissLoading()
                if (it.data != null) {
                    Logger.d(it)
                    mRootView?.setStoryDetailData(it)
                } else if (it.code == 101) {
                    mRootView?.showError(
                        "数据为空", ExceptionHandle.errorCode
                    )
                }
            } catch (e: Exception) {
                Logger.d(e)
                mRootView?.showError(
                    ExceptionHandle.handleException(e), ExceptionHandle.errorCode
                )
            }
        }
    }
}