package com.example.winiynews.mvp.model

import com.example.winiynews.bean.RecipeBean.RecipeDetailBean
import com.example.winiynews.http.RetrofitHub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author winiymissl
 * @Date 2024-07-30 21:31
 * @Version 1.0
 */
class RecipeDetailModel {
    fun getRecipeDetailData(id: String): Flow<RecipeDetailBean> {
        return flow {
            val detailBean = RetrofitHub.roll_api.getRecipeDetail(id = id)
            emit(detailBean)
        }
    }
}