package com.example.winiynews.http

import com.example.winiynews.bean.FoodHeatBean.FoodHeatDetail
import com.example.winiynews.bean.FoodHeatBean.SearchFoodHeatBean
import com.example.winiynews.bean.IdentifyBean.IdCardIdentifyBean
import com.example.winiynews.bean.RecipeBean.RecipeCategoryBean
import com.example.winiynews.bean.RecipeBean.SearchRecipeBean
import com.example.winiynews.bean.StoryBean.StoryCategoryBean
import com.example.winiynews.bean.beautyBean.BeautyBean
import com.example.winiynews.constant.HttpConstant
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
        @Query("app_id") app_id: String = HttpConstant.APPID,
        @Query("app_secret") app_secret: String = HttpConstant.APPSECRET,
        @Query("idcard") idcard: String
    ): Observable<IdCardIdentifyBean>

    @GET("api/image/girl/list/random?")
    fun getGirlList(
        @Query("app_id") app_id: String = HttpConstant.APPID,
        @Query("app_secret") app_secret: String = HttpConstant.APPSECRET
    ): Observable<BeautyBean>

    @GET("api/food_heat/food/search?")
    fun searchFoodHeatList(
        @Query("app_id") app_id: String = HttpConstant.APPID,
        @Query("app_secret") app_secret: String = HttpConstant.APPSECRET,
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
    ): Observable<SearchFoodHeatBean>

    @GET("api/food_heat/food/details?")
    fun getFoodHeatDetail(
        @Query("app_id") app_id: String = HttpConstant.APPID,
        @Query("app_secret") app_secret: String = HttpConstant.APPSECRET,
        @Query("foodId") foodId: String
    ): Observable<FoodHeatDetail>

    @GET("api/cookbook/search?")
    fun searchRecipe(
        @Query("app_id") app_id: String = HttpConstant.APPID,
        @Query("app_secret") app_secret: String = HttpConstant.APPSECRET,
        @Query("keyword") keyword: String,
        @Query("page") page: String
    ): Observable<SearchRecipeBean>

    @GET("api/cookbook/category?")
    fun getRecipeCategory(
        @Query("app_id") app_id: String = HttpConstant.APPID,
        @Query("app_secret") app_secret: String = HttpConstant.APPSECRET,
        @Query("category_id") category_id: String
    ): Observable<RecipeCategoryBean>

    @GET("api/story/types?")
    fun getStoryCategory(
        @Query("app_id") app_id: String = HttpConstant.APPID,
        @Query("app_secret") app_secret: String = HttpConstant.APPSECRET,
    ): Observable<StoryCategoryBean>
}