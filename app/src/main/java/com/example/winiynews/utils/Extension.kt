package com.example.winiynews.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.winiynews.application.MyApplication


/**
 * @Author winiymissl
 * @Date 2024-05-30 22:06
 * @Version 1.0
 */
fun Fragment.showToast(content: String): Toast {
    var toast = Toast.makeText(this.activity?.applicationContext, content, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
    return toast
}


fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(MyApplication.context, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}


fun View.dip2px(dipValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

fun View.px2dip(pxValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}
fun showDioLog(context: Context, data: () -> Unit) {
    data.invoke()
}
fun durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    return if (minute <= 9) {
        if (second <= 9) {
            "0$minute' 0$second''"
        } else {
            "0$minute' $second''"
        }
    } else {
        if (second <= 9) {
            "$minute' 0$second''"
        } else {
            "$minute' $second''"
        }
    }
}

/**
 * 数据流量格式化
 */
fun Context.dataFormat(total: Long): String {
    var result: String
    var speedReal: Int = (total / (1024)).toInt()
    result = if (speedReal < 512) {
        speedReal.toString() + " KB"
    } else {
        val mSpeed = speedReal / 1024.0
        (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
    }
    return result
}

/**
 * 检测流量是否连接
 */
fun isMobileAndWifiNetworkConnected(context: Context, type: Int): Boolean {
    if (context != null) {
        val networkInfo =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = networkInfo.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        when (type) {
            ConnectivityManager.TYPE_MOBILE -> {
                if (activeNetwork != null) {
                    return activeNetwork.isAvailable
                }
            }

            ConnectivityManager.TYPE_WIFI -> {
                if (activeNetwork != null) {
                    return activeNetwork.isAvailable
                }
            }

            else -> {
                throw IllegalArgumentException("Invalid type. ConnectivityManager.TYPE_MOBILE or ConnectivityManager.TYPE_WIFI")
            }
        }
    }
    return false
}

