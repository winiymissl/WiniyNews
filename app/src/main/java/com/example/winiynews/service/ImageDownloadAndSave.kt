package com.example.winiynews.service

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.winiynews.utils.isMobileAndWifiNetworkConnected
import com.hjq.toast.Toaster
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * @Author winiymissl
 * @Date 2024-07-21 14:16
 * @Version 1.0
 */


class ImageDownloadAndSave {
    private class DownloadImageTask(private val activity: Activity, private val imageUrl: String) :
        AsyncTask<Void?, Int?, Bitmap?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            if (!isMobileAndWifiNetworkConnected(
                    activity, ConnectivityManager.TYPE_MOBILE
                ) && !isMobileAndWifiNetworkConnected(activity, ConnectivityManager.TYPE_WIFI)
            ) {
                Toaster.show("网络状态异常!!!")
            }
//            cancel(true)
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            if (bitmap != null) {
                saveImageToGallery(activity, bitmap)
            } else {
                Toast.makeText(activity, "下载图片失败", Toast.LENGTH_SHORT).show()
            }
        }

        private fun saveImageToGallery(activity: Activity, bitmap: Bitmap) {
            val fileName = System.currentTimeMillis().toString() + ".jpg"
            try {
                val directory =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                if (!directory.exists()) {
                    directory.mkdirs()
                }
                val file = File(directory, fileName)
                val outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                outputStream.close()

                // 通知相册更新
                val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                val contentUri = Uri.fromFile(file)
                mediaScanIntent.setData(contentUri)
                activity.sendBroadcast(mediaScanIntent)

                Toast.makeText(activity, "图片已保存到相册", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(activity, "保存图片失败", Toast.LENGTH_SHORT).show()
            }
        }

        override fun doInBackground(vararg params: Void?): Bitmap? {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                connection.disconnect()
                return bitmap
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }
    }

    companion object {
        private const val REQUEST_STORAGE_PERMISSION = 1

        fun downloadAndSaveImage(activity: Activity, imageUrl: String) {
            // 检查存储权限
            if (ContextCompat.checkSelfPermission(
                    activity, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toaster.show("权限未通过!!!")
            } else {
                DownloadImageTask(activity, imageUrl).execute()
            }
        }
    }
}