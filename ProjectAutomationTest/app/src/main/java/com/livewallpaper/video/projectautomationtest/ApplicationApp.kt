package com.livewallpaper.video.projectautomationtest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.livewallpaper.video.projectautomationtest.domain.AppRoomDatabase

class ApplicationApp : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = base
    }

    companion object {
        var roomDatabase: AppRoomDatabase? = null

        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }
}
