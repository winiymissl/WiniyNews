package com.example.winiynews.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.hjq.toast.Toaster
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import kotlin.properties.Delegates


/**
 * @Author winiymissl
 * @Date 2024-05-30 20:39
 * @Version 1.0
 */
class MyApplication : Application() {
//    private var refWatcher: RefWatcher? = null

    companion object {

        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set

//        fun getRefWatcher(context: Context): RefWatcher? {
//            val myApplication = context.applicationContext as MyApplication
//            return myApplication.refWatcher
//        }

    }

    override fun onCreate() {
        super.onCreate()
        Toaster.init(this)
//        SkinCompatManager.withoutActivity(this)
//            .addInflater(SkinAppCompatViewInflater()) // 基础控件换肤初始化
//            .addInflater(SkinMaterialViewInflater()) // material design 控件换肤初始化[可选]
//            .addInflater(SkinConstraintViewInflater()) // ConstraintLayout 控件换肤初始化[可选]
//            .addInflater(SkinCardViewInflater()) // CardView v7 控件换肤初始化[可选]
//            .setSkinStatusBarColorEnable(false) // 关闭状态栏换肤，默认打开[可选]
//            .setSkinWindowBackgroundEnable(false) // 关闭windowBackground换肤，默认打开[可选]
//            .loadSkin()
        context = applicationContext

//        refWatcher = setupLeakCanary()
//        initConfig()
//        DisplayManager.init(this)
//        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
//        Glide.with(this).load("").into()
    }

//    private fun setupLeakCanary(): RefWatcher {
//        return if (LeakCanary.isInAnalyzerProcess(this)) {
//            RefWatcher.DISABLED
//        } else LeakCanary.install(this)
//    }


    /**
     * 初始化配置
     */
//    private fun initConfig() {
//
//        val formatStrategy = PrettyFormatStrategy.newBuilder()
//            .showThreadInfo(false)  // 隐藏线程信息 默认：显示
//            .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
//            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//            .tag("hao_zz")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
//            .build()
//        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
//            override fun isLoggable(priority: Int, tag: String?): Boolean {
//                return BuildConfig.DEBUG
//            }
//        })
//    }


    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }
}