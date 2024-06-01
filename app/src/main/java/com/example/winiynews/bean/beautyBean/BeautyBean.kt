package com.example.winiynews.bean.beautyBean

/**
 * @Author winiymissl
 * @Date 2024-05-31 16:37
 * @Version 1.0
 */
data class BeautyBean(
    val code: Int,
    val data: List<Data>,
    val msg: String
)

data class Data(
    val imageFileLength: Int,
    val imageSize: String,
    val imageUrl: String
)