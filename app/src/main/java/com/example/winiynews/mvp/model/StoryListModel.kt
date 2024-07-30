package com.example.winiynews.mvp.model

import com.example.winiynews.bean.StoryBean.StoryListBean
import com.example.winiynews.constant.HttpConstant
import com.example.winiynews.http.RetrofitHub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author winiymissl
 * @Date 2024-07-29 16:34
 * @Version 1.0
 */
class StoryListModel {
    fun getListStory(
        typeId: String, page: Int
    ): Flow<StoryListBean> {
        return flow {
            var data = RetrofitHub.roll_api.getStoryList(
                HttpConstant.APPID, HttpConstant.APPSECRET, typeId.toInt(), page
            )
            emit(data)
        }
    }
}