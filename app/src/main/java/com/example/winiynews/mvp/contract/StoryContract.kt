package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.StoryBean.StoryCategoryBean

/**
 * @Author winiymissl
 * @Date 2024-07-22 16:18
 * @Version 1.0
 */
interface StoryContract {
    interface View : IBaseView {
        fun setStoryData(data: StoryCategoryBean)
        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        fun requestStoryData()
    }
}