package com.example.winiynews.bean.FoodHeatBean


/**
 * @Author winiymissl
 * @Date 2024-06-01 16:48
 * @Version 1.0
 */
data class SearchFoodHeatBean(
    val code: Int, val data: Data, val msg: String
)

data class Data(
    val limit: Int,
    val list: List<FData>,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class FData(
    val calory: String, val foodId: String, val healthLevel: Int, val name: String
)