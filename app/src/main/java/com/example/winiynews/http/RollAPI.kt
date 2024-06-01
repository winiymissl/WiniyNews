package com.example.winiynews.http

import com.example.winiynews.bean.FoodHeatBean.SearchFoodHeatBean
import com.example.winiynews.bean.IdentifyBean.IdCardIdentifyBean
import com.example.winiynews.bean.beautyBean.BeautyBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author winiymissl
 * @Date 2024-05-30 17:15
 * @Version 1.0
 */
interface RollAPI {
    @GET("api/idcard/search?")
    fun idCardIdentify(
        @Query("app_id") app_id: String = "hv1asqfhrgmty2gc",
        @Query("app_secret") app_secret: String = "N4EBbFemeY4EgkCVm5RqyTY3xbLd58PZ",
        @Query("idcard") idcard: String
    ): Observable<IdCardIdentifyBean>


    @GET("api/image/girl/list/random?")
    fun getGirlList(
        @Query("app_id") app_id: String = "hv1asqfhrgmty2gc",
        @Query("app_secret") app_secret: String = "N4EBbFemeY4EgkCVm5RqyTY3xbLd58PZ"
    ): Observable<BeautyBean>


    @GET("api/food_heat/food/search?")
    fun searchFoodHeatList(
        @Query("app_id") app_id: String = "hv1asqfhrgmty2gc",
        @Query("app_secret") app_secret: String = "N4EBbFemeY4EgkCVm5RqyTY3xbLd58PZ",
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
    ): Observable<SearchFoodHeatBean>
//    @GET("api/food_heat/food/search?")
//    fun searchFoodHeatList(
//        @Query("app_id") app_id: String = "hv1asqfhrgmty2gc",
//        @Query("app_secret") app_secret: String = "N4EBbFemeY4EgkCVm5RqyTY3xbLd58PZ",
//        @Query("keyword") keyword: String,
//        @Query("page") page: Int,
//    ): Call<SearchFoodHeatBean>
}