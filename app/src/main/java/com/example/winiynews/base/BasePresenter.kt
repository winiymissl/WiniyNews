package com.example.winiynews.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @Author winiymissl
 * @Date 2024-05-30 19:18
 * @Version 1.0
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {
    /**
     * 将set方法私有
     */
    var mRootView: T? = null
        private set

    /**
     * 帮助开发者方便地管理多个 Disposable 对象
     */
    private var compositeDisposable = CompositeDisposable()

    /**
     * fragment是否附着到activity中
     * */
    private val isViewAttached: Boolean
        get() = mRootView != null


    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    /**
     * 可以学习定义异常
     */
    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}