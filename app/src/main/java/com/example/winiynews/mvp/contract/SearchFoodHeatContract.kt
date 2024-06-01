package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.FoodHeatBean.SearchFoodHeatBean

/**
 * @Author winiymissl
 * @Date 2024-06-01 16:47
 * @Version 1.0
 */
interface SearchFoodHeatContract {
    interface View : IBaseView {
        /**
         * 设置请求的数据
         */
        fun setSearchFoodHeatData(bean: SearchFoodHeatBean)

        fun setFoodDetailData(id: String)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)
    }

    /**
     * 给presenter的接口
     */
    interface Presenter {
        /**
         * 获取girls
         */
        fun requestFoodHeatData(name: String, page: Int)
    }
}