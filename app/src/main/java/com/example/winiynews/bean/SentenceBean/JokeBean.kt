package com.example.winiynews.bean.SentenceBean

data class JokeBean(
    val code: Int,
    val data: List<Data>,
    val msg: String
)

data class Data(
    val content: String,
    val updateTime: String
)