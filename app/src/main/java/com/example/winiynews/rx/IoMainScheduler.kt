package com.example.winiynews.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @Author winiymissl
 * @Date 2024-05-30 21:04
 * @Version 1.0
 */
class IoMainScheduler<T : Any> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())