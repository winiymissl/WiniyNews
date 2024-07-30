package com.example.winiynews.http

import com.example.winiynews.constant.HttpConstant

/**
 * @Author winiymissl
 * @Date 2024-05-29 15:11
 * @Version 1.0
 */
object RetrofitHub {
    val api: WanAndroidAPI = RetrofitFactory.rxJavaRetrofit(HttpConstant.base_url)
        .create(WanAndroidAPI::class.java)
    val roll_api: RollAPI =
        RetrofitFactory.rxJavaRetrofit(HttpConstant.base_url_roll).create(RollAPI::class.java)
    val baidu_api: BaiduAPI =
        RetrofitFactory.rxJavaRetrofit(HttpConstant.base_url_baidu).create(BaiduAPI::class.java)
}