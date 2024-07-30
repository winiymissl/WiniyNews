package com.example.winiynews.bean.StoryBean

/**
 * @Author winiymissl
 * @Date 2024-07-29 21:29
 * @Version 1.0
 */
data class StoryDetailBean(
    val code: Int,
    val data: DData,
    val msg: String
)

data class DData(
    val content: String,
    val length: Int,
    val readTime: String,
    val storyId: Int,
    val title: String,
    val type: String
)