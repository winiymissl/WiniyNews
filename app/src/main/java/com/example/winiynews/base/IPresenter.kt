package com.example.winiynews.base

/**
 * @Author winiymissl
 * @Date 2024-05-30 19:21
 * @Version 1.0
 */
interface IPresenter<in V : IBaseView> {
    fun attachView(mRootView: V)
    fun detachView()
}