package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.BottomFoodHeatDetailContract
import com.example.winiynews.mvp.model.BottomFoodHeatDetailModel
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-06-02 13:27
 * @Version 1.0
 */
class BottomFoodHeatDetailPresenter : BasePresenter<BottomFoodHeatDetailContract.View>(),
    BottomFoodHeatDetailContract.Presenter {
    private val model: BottomFoodHeatDetailModel by lazy {
        BottomFoodHeatDetailModel()
    }

    override fun requestFoodHeatDetailData(foodId: String) {
        checkViewAttached()
        mRootView?.showLoading()
        model.getFoodDetailData(foodId).subscribe({
            mRootView?.apply {
                Logger.d(it)
                dismissLoading()
                setFoodDetailData(it)
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