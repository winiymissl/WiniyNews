package com.example.winiynews.mvp.presenter

import com.example.winiynews.base.BasePresenter
import com.example.winiynews.http.exception.ExceptionHandle
import com.example.winiynews.mvp.contract.SearchFoodHeatContract
import com.example.winiynews.mvp.model.SearchFoodHeatModel
import com.orhanobut.logger.Logger

/**
 * @Author winiymissl
 * @Date 2024-06-01 18:25
 * @Version 1.0
 */
class SearchFoodHeatPresenter : BasePresenter<SearchFoodHeatContract.View>(),
    SearchFoodHeatContract.Presenter {
    private val model: SearchFoodHeatModel by lazy {
        SearchFoodHeatModel()
    }

    override fun requestFoodHeatData(name: String, page: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        model.searchFoodHeat(name, page).subscribe({
            Logger.d(it.toString())
            mRootView?.apply {
                dismissLoading()
                setSearchFoodHeatData(it)
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