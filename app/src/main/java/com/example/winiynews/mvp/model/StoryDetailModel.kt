package com.example.winiynews.mvp.model

import com.example.winiynews.bean.StoryBean.StoryDetailBean
import com.example.winiynews.http.RetrofitHub
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author winiymissl
 * @Date 2024-07-29 21:27
 * @Version 1.0
 */
class StoryDetailModel {
    suspend fun getStoryDetailData(id: String): Flow<StoryDetailBean> {
        return flow {
            val detail = RetrofitHub.roll_api.getStoryDetail(story_id = id.toInt())
            Logger.d(detail)
            emit(detail)
        }
    }
}