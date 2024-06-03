package com.example.winiynews.mvp.model

import com.example.winiynews.bean.FoodHeatBean.FoodHeatDetail
import com.example.winiynews.http.RetrofitHub
import com.example.winiynews.rx.SchedulerUtils
import io.reactivex.rxjava3.core.Observable

/**
 * @Author winiymissl
 * @Date 2024-06-02 13:25
 * @Version 1.0
 */
class BottomFoodHeatDetailModel {
    fun getFoodDetailData(id: String): Observable<FoodHeatDetail> {
        return RetrofitHub.roll_api.getFoodHeatDetail(foodId = id)
            .compose(SchedulerUtils.ioToMain())
    }
}