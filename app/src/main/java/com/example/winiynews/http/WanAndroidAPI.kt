package com.example.winiynews.http

import com.example.winiynews.bean.ArticleListBean.ArticleListBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author winiymissl
 * @Date 2024-05-29 12:06
 * @Version 1.0
 */
interface WanAndroidAPI {
    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<ArticleListBean>
}