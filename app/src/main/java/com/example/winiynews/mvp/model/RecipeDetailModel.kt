package com.example.winiynews.mvp.model

import com.example.winiynews.bean.RecipeBean.RecipeDetailBean
import com.example.winiynews.http.RetrofitHub
import com.example.winiynews.rx.SchedulerUtils
import io.reactivex.rxjava3.core.Observable

/**
 * @Author winiymissl
 * @Date 2024-07-30 21:31
 * @Version 1.0
 */
class RecipeDetailModel {
    fun getRecipeDetailData(id: String): Observable<RecipeDetailBean> {
        return RetrofitHub.roll_api.getRecipeDetail(id = id).compose(SchedulerUtils.ioToMain())
    }
}