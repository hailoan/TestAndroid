package com.livewallpaper.video.projectautomationtest

import android.Manifest.permission
import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.PowerManager
import android.provider.Settings
import androidx.annotation.Nullable
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat


object PowerSaverHelper {
    enum class PowerSaveState {
        ON, OFF, ERROR_GETTING_STATE, IRRELEVANT_OLD_ANDROID_API
    }

    enum class WhiteListedInBatteryOptimizations {
        WHITE_LISTED, NOT_WHITE_LISTED, ERROR_GETTING_STATE, UNKNOWN_TOO_OLD_ANDROID_API_FOR_CHECKING, IRRELEVANT_OLD_ANDROID_API
    }

    enum class DozeState {
        NORMAL_INTERACTIVE, DOZE_TURNED_ON_IDLE, NORMAL_NON_INTERACTIVE, ERROR_GETTING_STATE, IRRELEVANT_OLD_ANDROID_API, UNKNOWN_TOO_OLD_ANDROID_API_FOR_CHECKING
    }

    fun getDozeState(context: Context): DozeState {
        if (VERSION.SDK_INT < VERSION_CODES.M) {
            return DozeState.UNKNOWN_TOO_OLD_ANDROID_API_FOR_CHECKING
        }
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return if (pm.isDeviceIdleMode) DozeState.DOZE_TURNED_ON_IDLE else if (pm.isInteractive) DozeState.NORMAL_INTERACTIVE else DozeState.NORMAL_NON_INTERACTIVE
    }

    fun getPowerSaveState(context: Context): PowerSaveState {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return if (pm.isPowerSaveMode) PowerSaveState.ON else PowerSaveState.OFF
    }


    fun getIfAppIsWhiteListedFromBatteryOptimizations(
        context: Context,
        packageName: String,
    ): WhiteListedInBatteryOptimizations {
        if (VERSION.SDK_INT < VERSION_CODES.M) return WhiteListedInBatteryOptimizations.UNKNOWN_TOO_OLD_ANDROID_API_FOR_CHECKING
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return if (pm.isIgnoringBatteryOptimizations(packageName)) WhiteListedInBatteryOptimizations.WHITE_LISTED else WhiteListedInBatteryOptimizations.NOT_WHITE_LISTED
    }

    @TargetApi(VERSION_CODES.M)
    @RequiresPermission(permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
    @Nullable
    fun prepareIntentForWhiteListingOfBatteryOptimization(
        context: Context,
        packageName: String,
        alsoWhenWhiteListed: Boolean,
    ): Intent? {
        if (ContextCompat.checkSelfPermission(context,
                permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) == PackageManager.PERMISSION_DENIED
        ) return null
        val appIsWhiteListedFromPowerSave =
            getIfAppIsWhiteListedFromBatteryOptimizations(context, packageName)
        var intent: Intent? = null
        when (appIsWhiteListedFromPowerSave) {
            WhiteListedInBatteryOptimizations.WHITE_LISTED -> if (alsoWhenWhiteListed) intent =
                Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            WhiteListedInBatteryOptimizations.NOT_WHITE_LISTED -> intent =
                Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).setData(Uri.parse("package:$packageName"))
            WhiteListedInBatteryOptimizations.ERROR_GETTING_STATE, WhiteListedInBatteryOptimizations.UNKNOWN_TOO_OLD_ANDROID_API_FOR_CHECKING, WhiteListedInBatteryOptimizations.IRRELEVANT_OLD_ANDROID_API -> {}
        }
        return intent
    }

    /**
     * registers a receiver to listen to power-save events. returns true iff succeeded to register the broadcastReceiver.
     */
    @TargetApi(VERSION_CODES.M)
    fun registerPowerSaveReceiver(context: Context, receiver: BroadcastReceiver): Boolean {
        if (VERSION.SDK_INT < VERSION_CODES.M) return false
        val filter = IntentFilter()
        filter.addAction(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED)
        context.registerReceiver(receiver, filter)
        return true
    }
}