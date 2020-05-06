package com.sjq.apkbsdiff

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class MyApplication: MultiDexApplication() {

    companion object{
        var context:Application?= null
        fun getApplication(): Context {
            return context!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        //MultiDex分包
        MultiDex.install(this);
    }
}