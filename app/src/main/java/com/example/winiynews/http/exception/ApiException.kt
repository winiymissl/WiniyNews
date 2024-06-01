package com.example.winiynews.http.exception

/**
 * @Author winiymissl
 * @Date 2024-05-30 21:35
 * @Version 1.0
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}