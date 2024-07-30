package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.StoryBean.StoryDetailBean
import com.example.winiynews.bean.StoryBean.StoryListBean

/**
 * @Author winiymissl
 * @Date 2024-07-29 21:28
 * @Version 1.0
 */
interface StoryDetailContract {
    interface View : IBaseView {
        suspend fun setStoryDetailData(data: StoryDetailBean)
        suspend  fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        suspend fun requestStoryDetailData(storyId: String)
    }
}