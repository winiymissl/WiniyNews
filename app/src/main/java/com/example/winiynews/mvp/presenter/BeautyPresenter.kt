package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.BeautyContract
import com.example.winiynews.mvp.model.BeautyModel
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-05-31 16:40
 * @Version 1.0
 */
class BeautyPresenter : BasePresenter<BeautyContract.View>(), BeautyContract.Presenter {
    private val model: BeautyModel by lazy {
        BeautyModel()
    }

    override fun requestBeautyData() {
        /**
         * 检测是否绑定view
         */
        checkViewAttached()
        mRootView?.showLoading()
        model.getGirlList().subscribe({
            mRootView?.apply {
                dismissLoading()
                setBeautyData(it)
            }
        }, {
            Logger.d(it)
            mRootView?.apply {
                showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
            }
        })
    }

    override fun requestFreshData() {
        checkViewAttached()
        mRootView?.showLoading()
        model.getGirlList().subscribe({
            mRootView?.apply {
                dismissLoading()
                refresh()
            }
        }, {
            Logger.d(it)
            mRootView?.apply {
                showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
            }
        })
    }

}