package com.example.bsafes

import android.content.Context
import android.util.Log
import java.io.File

fun deleteTemporaryMediaFiles(context: Context) {
    val tag = "CapturedMediaCleaner"
    //Diretory where temporary files are stored
    val tempDir = File(context.cacheDir, "captured_media")
    //List files in the directory
    val tempFiles = tempDir.listFiles()
    if (tempFiles != null && tempFiles.isNotEmpty()) {
        Log.d(tag, "Media captured in previous executions: ")
        tempFiles.forEach { file ->
            Log.d(
                tag,
                "Deleting file: ${file.name}, Path: ${file.absolutePath}, Size: ${file.length()} bytes."
            )
            file.delete()
            Log.d(tag, "File deleted: ${file.name}")
        }
    } else {
        Log.d("MainActivity", "No temporary files found.")
    }
}