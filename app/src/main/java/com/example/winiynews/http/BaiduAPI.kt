package com.example.winiynews.http

import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Author winiymissl
 * @Date 2024-07-30 15:33
 * @Version 1.0
 */
interface BaiduAPI {
    /**
     * 实体分析
     */
    @Headers("Content-Type:application/json")
    @POST("entity_analysis?")
    fun entityAnalysis(
        @Query("access_token") token: String, @Field("text") text: String
    )
}