package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.beautyBean.BeautyBean

/**
 * @Author winiymissl
 * @Date 2024-05-31 16:30
 * @Version 1.0
 */
interface BeautyContract {
    interface View : IBaseView {
        /**
         * 设置第一次请求的数据
         */
        fun setBeautyData(bean: BeautyBean)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

        fun refresh()
    }

    /**
     * 给presenter的接口
     */
    interface Presenter {
        /**
         * 获取girls
         */
        fun requestBeautyData()
        fun requestFreshData()
    }
}