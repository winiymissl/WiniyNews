package com.example.winiynews.mvp.model

import com.example.winiynews.bean.FoodHeatBean.SearchFoodHeatBean
import com.example.winiynews.http.RetrofitHub
import com.example.winiynews.rx.SchedulerUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @Author winiymissl
 * @Date 2024-06-01 18:18
 * @Version 1.0
 */
class SearchFoodHeatModel {
    fun searchFoodHeat(foodName: String, page: Int): Observable<SearchFoodHeatBean> {
        return RetrofitHub.roll_api.searchFoodHeatList(keyword = foodName, page = page)
            .compose(SchedulerUtils.ioToMain())

    }
}