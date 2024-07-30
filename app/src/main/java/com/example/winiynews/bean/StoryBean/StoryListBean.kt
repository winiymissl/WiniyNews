package com.example.winiynews.bean.StoryBean

/**
 * @Author winiymissl
 * @Date 2024-07-29 16:07
 * @Version 1.0
 */
data class StoryListBean(
    val code: Int,
    val data: List<SData>,
    val msg: String
)

data class SData(
    val length: Int,
    val readTime: String,
    val storyId: Int,
    val title: String,
    val type: String
)