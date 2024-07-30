package com.example.winiynews.mvp.model

import com.example.winiynews.bean.SentenceBean.JokeBean
import com.example.winiynews.constant.HttpConstant
import com.example.winiynews.http.RetrofitHub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author winiymissl
 * @Date 2024-07-23 15:29
 * @Version 1.0
 */
class JokeModel {
    suspend fun getSentenceData(): Flow<JokeBean> {
        return flow {
            var bean: JokeBean? = null
            bean = RetrofitHub.roll_api.getJokeList(HttpConstant.APPID, HttpConstant.APPSECRET)
            emit(bean)
        }
    }
}