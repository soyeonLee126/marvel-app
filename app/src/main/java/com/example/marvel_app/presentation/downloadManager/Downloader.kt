package com.example.marvel_app.presentation.downloadManager

interface Downloader {
    fun downloadImage(url: String, name:String): Long
}