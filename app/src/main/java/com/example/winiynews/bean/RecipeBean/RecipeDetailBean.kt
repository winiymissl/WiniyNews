package com.example.winiynews.bean.RecipeBean

/**
 * @Author winiymissl
 * @Date 2024-07-30 19:48
 * @Version 1.0
 */
data class RecipeDetailBean(
    val code: Int, val data: DData, val msg: String
)

data class DData(
    val commentCount: Int,
    val difficulty: String,
    val duration: String,
    val id: Int,
    val ingredient: List<Ingredient>,
    val instruction: List<Instruction>,
    val name: String,
    val tips: String
)

data class DIngredient(
    val amount: String, val name: String
)

data class Instruction(
    val step: String, val text: String, val url: String
)