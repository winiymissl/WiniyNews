package com.example.winiynews.service

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


/**
 * @Author winiymissl
 * @Date 2024-07-20 16:49
 * @Version 1.0
 */


class ImageDownloadAndSaveWithProgress {

    companion object {
        private val REQUEST_STORAGE_PERMISSION = 1
        fun downloadAndSaveImage(
            activity: Activity?, imageUrl: String?, progressBar: ProgressBar?
        ) {
            // 检查存储权限
            if (ContextCompat.checkSelfPermission(
                    activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_STORAGE_PERMISSION
                )
            } else {
                if (imageUrl != null && progressBar != null) {
                    DownloadImageTask(activity, progressBar, imageUrl).execute()
                }
            }
        }

        fun saveImageToGallery(activity: Activity, bitmap: Bitmap) {
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
                com.orhanobut.logger.Logger.d(e.toString())
                Toast.makeText(activity, "保存图片失败", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private class DownloadImageTask(
        private val activity: Activity,

        private val progressBar: ProgressBar, private val imageUrl: String
    ) : AsyncTask<Void?, Int?, Bitmap?>() {


        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.progress = values[0]!!
        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }
        override fun onPostExecute(bitmap: Bitmap?) {
            progressBar.visibility = View.GONE
            if (bitmap != null) {
                saveImageToGallery(activity, bitmap)
            } else {
                Toast.makeText(activity, "下载图片失败", Toast.LENGTH_SHORT).show()
            }
        }

        override fun doInBackground(vararg params: Void?): Bitmap? {
            try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()
                val fileLength = connection.contentLength
                val inputStream: InputStream = BufferedInputStream(connection.inputStream)
                val outputStream = FileOutputStream(
                    File(
                        activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        "image.jpg"
                    )
                )
                val data = ByteArray(1024)
                var total = 0
                var count: Int
                while ((inputStream.read(data).also { count = it }) != -1) {
                    total += count
                    publishProgress(((total * 100) / fileLength))
                    outputStream.write(data, 0, count)
                }

                outputStream.close()
                inputStream.close()

                return BitmapFactory.decodeFile(
                    File(
                        activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image.jpg"
                    ).absolutePath
                )
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }

    }
}