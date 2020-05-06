package com.sjq.apkbsdiff

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.lang.Exception



object InstallApkUti {

     fun createNewApk(): File {

        var newApk = File(Environment.getExternalStorageDirectory(),"bsdiff.apk")
        if(!newApk.exists()){
            try {
                newApk.createNewFile()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        return newApk
    }


     fun install(filePath: String,context: Context) {
        Log.i("-------", "开始执行安装: $filePath")
        val apkFile = File(filePath)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w("-------", "版本大于 N ，开始使用 fileProvider 进行安装")
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val contentUri = FileProvider.getUriForFile(
                context, "com.sjq.apkbsdiff.fileprovider", apkFile
            )
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
        } else {
            Log.w("-------", "正常进行安装")
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        }
        context.startActivity(intent)
    }

}