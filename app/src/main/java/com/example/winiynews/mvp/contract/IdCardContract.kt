package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.IdentifyBean.IdCardIdentifyBean

/**
 * @Author winiymissl
 * @Date 2024-05-30 19:07
 * @Version 1.0
 */
interface IdCardContract {
    interface View : IBaseView {
        /**
         * 设置第一次请求的数据
         */
        fun setIdCardData(bean: IdCardIdentifyBean)

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
         * 获取首页精选数据
         */
        fun requestIdCardData(id: String)
    }
}