package com.example.winiynews.http

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author winiymissl
 * @Date 2024-05-29 13:52
 * @Version 1.0
 */
class RetrofitFactory {
    companion object {
        fun rxJavaRetrofit(base_url: String): Retrofit {
            return Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
        }
    }
}