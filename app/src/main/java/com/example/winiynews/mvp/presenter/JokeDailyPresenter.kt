package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.JokeContract
import com.example.winiynews.mvp.model.JokeModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * @Author winiymissl
 * @Date 2024-07-22 21:04
 * @Version 1.0
 */
class JokeDailyPresenter : BasePresenter<JokeContract.View>(), JokeContract.Presenter {
    private val model: JokeModel by lazy { JokeModel() }
    override suspend fun requestSentenceData() {
        checkViewAttached()
        mRootView?.showLoading()
        model.getSentenceData().flowOn(Dispatchers.IO).catch {
            mRootView?.showError(
                ExceptionHandle.handleException(it),
                ExceptionHandle.errorCode
            )
        }.collect {
            try {
                mRootView?.setSentenceData(it)
                mRootView?.dismissLoading()
            } catch (e: Exception) {
                Logger.d(e)
            }
        }
    }
}