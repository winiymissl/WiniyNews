package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.IdCardContract
import com.example.winiynews.mvp.model.IdCardIdentifyModel
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-05-30 21:25
 * @Version 1.0
 */
class IdCardIdentifyPresenter : BasePresenter<IdCardContract.View>(), IdCardContract.Presenter {

    private val model: IdCardIdentifyModel by lazy {
        IdCardIdentifyModel()
    }

    override fun requestIdCardData(id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        model.toIdentify(id).subscribe({ bean ->
            mRootView?.apply {
                dismissLoading()
                setIdCardData(bean)
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