package com.example.marvel_app.presentation.downloadManager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DownloadCompletedReceiver: BroadcastReceiver() {

    private lateinit var downloadManager: DownloadManager
    override fun onReceive(p0: Context?, p1: Intent?) {
        downloadManager = p0?.getSystemService(DownloadManager::class.java)!!
        if(p1?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = p1.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if(id != -1L) {
                println("Download with ID $id finished!")
            }
        }
    }
}