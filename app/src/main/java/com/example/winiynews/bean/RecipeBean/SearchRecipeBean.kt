package com.example.winiynews.bean.RecipeBean

/**
 * @Author winiymissl
 * @Date 2024-06-02 14:49
 * @Version 1.0
 */
data class SearchRecipeBean(
    val code: Int, val data: SearchData, val msg: String
)

data class SearchData(
    val limit: Int,
    val list: List<RecipeItemData>,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class RecipeItemData(
    val cover: String,
    val desc: String,
    val id: Int,
    val ingredient: List<Ingredient>,
    val name: String
)

data class Ingredient(
    val name: String
)