package com.example.winiynews.bean.RecipeBean

/**
 * @Author winiymissl
 * @Date 2024-06-02 14:48
 * @Version 1.0
 */
data class RecipeCategoryBean(
    val code: Int, val data: MutableList<Data>, val msg: String
)

data class Data(
    val floor: Int, val id: Int, val name: String
)