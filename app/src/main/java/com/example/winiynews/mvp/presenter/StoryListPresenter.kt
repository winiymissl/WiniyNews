package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.StoryListContract
import com.example.winiynews.mvp.model.StoryListModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * @Author winiymissl
 * @Date 2024-07-29 16:38
 * @Version 1.0
 */
class StoryListPresenter : StoryListContract.Presenter, BasePresenter<StoryListContract.View>() {
    private val model by lazy {
        StoryListModel()
    }

    override suspend fun requestStoryListData(typeId: String, page: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        model.getListStory(typeId, page).flowOn(Dispatchers.IO).catch {
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

                    mRootView?.setStoryListData(it)
                } else if (it.code == 101) {
                    mRootView?.showError(
                        "加载失败", ExceptionHandle.errorCode
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