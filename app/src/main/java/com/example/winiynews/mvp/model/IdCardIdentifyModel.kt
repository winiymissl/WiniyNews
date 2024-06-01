package com.example.winiynews.mvp.model

import com.example.winiynews.bean.IdentifyBean.IdCardIdentifyBean
import com.example.winiynews.http.RetrofitHub
import com.example.winiynews.rx.SchedulerUtils
import io.reactivex.rxjava3.core.Observable

/**
 * @Author winiymissl
 * @Date 2024-05-30 19:11
 * @Version 1.0
 */
class IdCardIdentifyModel {
    fun toIdentify(id: String): Observable<IdCardIdentifyBean> {
        return RetrofitHub.roll_api.idCardIdentify(idcard = id).compose(SchedulerUtils.ioToMain())
    }
}