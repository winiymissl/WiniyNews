package com.example.winiynews.mvp.model

import com.example.winiynews.bean.SentenceBean.JokeBean
import com.example.winiynews.constant.HttpConstant
import com.example.winiynews.http.RetrofitHub
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author winiymissl
 * @Date 2024-07-23 15:29
 * @Version 1.0
 */
class JokeModel {
    suspend fun getSentenceData(): Flow<JokeBean> {
        return flow<JokeBean> {
            var bean: JokeBean? = null
            runCatching {
                bean = RetrofitHub.roll_api.getJokeList(HttpConstant.APPID, HttpConstant.APPSECRET)
            }.onSuccess {
                bean?.let { it1 -> emit(it1) }
            }.onFailure {
                Logger.e(it.message.toString())
            }
        }
    }
}