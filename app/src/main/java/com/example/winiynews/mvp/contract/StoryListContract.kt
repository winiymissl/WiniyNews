package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.StoryBean.StoryListBean

/**
 * @Author winiymissl
 * @Date 2024-07-29 16:33
 * @Version 1.0
 */
interface StoryListContract {
    interface View : IBaseView {
        suspend fun setStoryListData(data: StoryListBean)
        suspend fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        suspend fun requestStoryListData(typeId: String, page: Int)
    }
}