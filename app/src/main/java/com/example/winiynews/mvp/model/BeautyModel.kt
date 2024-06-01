package com.example.winiynews.mvp.model

import com.example.winiynews.bean.beautyBean.BeautyBean
import com.example.winiynews.http.RetrofitHub
import com.example.winiynews.rx.SchedulerUtils
import io.reactivex.rxjava3.core.Observable

/**
 * @Author winiymissl
 * @Date 2024-05-31 16:39
 * @Version 1.0
 */
class BeautyModel {
    fun getGirlList(): Observable<BeautyBean> {
        return RetrofitHub.roll_api.getGirlList().compose(SchedulerUtils.ioToMain())
    }
}