package com.example.winiynews.bean.IdentifyBean

/**
 * @Author winiymissl
 * @Date 2024-05-30 17:16
 * @Version 1.0
 */
data class IdCardIdentifyBean(
    val code: Int,
    val data: Data,
    val msg: String
)

data class Data(
    val address: String,
    val birthday: String,
    val idCardNum: String,
    val sex: String
)

