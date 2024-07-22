package com.example.winiynews.mvp.model

import com.example.winiynews.bean.StoryBean.StoryCategoryBean
import com.example.winiynews.http.RetrofitHub
import com.example.winiynews.rx.SchedulerUtils
import io.reactivex.rxjava3.core.Observable

class StoryCategoryModel {
    fun getStoryCategory(): Observable<StoryCategoryBean> {
        return RetrofitHub.roll_api.getStoryCategory().compose(SchedulerUtils.ioToMain())
    }
}