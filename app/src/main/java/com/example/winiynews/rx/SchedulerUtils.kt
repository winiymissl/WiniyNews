package com.example.winiynews.rx

/**
 * @Author winiymissl
 * @Date 2024-05-30 21:04
 * @Version 1.0
 */
object SchedulerUtils {
    fun <T : Any> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}