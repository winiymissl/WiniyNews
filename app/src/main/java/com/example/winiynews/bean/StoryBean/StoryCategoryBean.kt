package com.example.winiynews.bean.StoryBean

data class StoryCategoryBean(
    val code: Int,
    val data: List<Data>,
    val msg: String
)

data class Data(
    val name: String,
    val type_id: Int
)