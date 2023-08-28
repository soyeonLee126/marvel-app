package com.example.marvel_app.presentation.downloadManager

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class ImageDownloader (
    private val context: Context,
): Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    override fun downloadImage(url: String, name:String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("${name}.jpg")
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image/jpg")
        return downloadManager.enqueue(request)
    }
}