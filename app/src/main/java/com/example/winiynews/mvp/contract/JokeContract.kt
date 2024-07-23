package com.example.winiynews.mvp.contract

import com.example.winiynews.base.IBaseView
import com.example.winiynews.bean.SentenceBean.JokeBean

/**
 * @Author winiymissl
 * @Date 2024-07-22 20:58
 * @Version 1.0
 */
interface JokeContract {
    interface View : IBaseView {
        suspend fun setSentenceData(bean: JokeBean)
        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter {
        suspend fun requestSentenceData()
    }
}