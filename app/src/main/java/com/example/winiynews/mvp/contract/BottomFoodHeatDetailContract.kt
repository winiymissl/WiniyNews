package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.FoodHeatBean.FoodHeatDetail

/**
 * @Author winiymissl
 * @Date 2024-06-02 13:24
 * @Version 1.0
 */
interface BottomFoodHeatDetailContract {
    interface View : IBaseView {
        fun setFoodDetailData(detail: FoodHeatDetail)
        fun showError(msg: String, errorCode: Int)

    }

    interface Presenter {
        fun requestFoodHeatDetailData(id: String)
    }
}