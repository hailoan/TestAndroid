package com.livewallpaper.video.projectautomationtest

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private var device: UiDevice
    private var context: Context

    init {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)
        context = instrumentation.context

    }

    @Before
    fun onSetup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

    }

    @After
    fun tearDown() {
        // Press Home key after running the test
        device.pressHome()
    }

    @Test
    fun downloadOtherApplication() {
//        device.pressHome()
//        showInMarket("com.linecorp.b612.android")
//        device.wait(Until.hasObject(By.text("Google Play Store")), 2000)
//        val requestApp = device.hasObject(By.text("Google Play Store"))
//        if (requestApp) {
//            val permission = device.findObject(By.text("Google Play Store"))
//            permission.parent.click()
//        }
        device.waitForIdle(5000)
//        val googlePlay = "com.android.vending"
//        val googlePlay = "com.coloros.calculator"
//        startApplication(googlePlay)
//        device.wait(Until.hasObject(By.pkg("com.android.vending")), 2000)
//        device.findObject(By.res("com.android.vending:id/0_resource_name_obfuscated")).click()
//        device.findObjects(By.clazz("android.support.v7.widget.RecyclerView").res("com.android.vending:id/0_resource_name_obfuscated"))[1].click()

//        device.findObject(By.text("Settings")).click()
//        val uiScroll = UiScrollable(UiSelector().scrollable(true))
//        device.findObjects(By.clazz("android.support.v7.widget.RecyclerView").res("com.android.vending:id/0_resource_name_obfuscated"))[0].click()
//        uiScroll.scrollForward()
//            device.findObject(By.clazz("androidx.compose.ui.platform.ComposeView")).
//        composeTestRule.onNodeWithContentDescription("Uninstall").onParent().performClick()
//        startApplication("com.android.vending")
//        val search = device.findObject(By.text("Search for apps & games"))
//        search.click()
//        val edtSearch = device.wait(Until.findObject(By.res("com.android.vending:id/0_resource_name_obfuscated")), 1000)
//        edtSearch.text = "camera"
//        val list = device.wait(Until.findObject(By.res("com.android.vending:id/0_resource_name_obfuscated")), 2000)
//        list.children[0].click()
    }

    @Test
    fun powerSavingMode() {
        device.pressHome()
            var intent = Intent()

        if (Build.MANUFACTURER == "samsung") {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                intent.component = ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                intent.component = ComponentName("com.samsung.android.sm", "com.samsung.android.sm.ui.battery.BatteryActivity")
            }

            try {
                context.startActivity(intent);
            } catch (ex: ActivityNotFoundException) {
                // Fallback to global settings
                intent = Intent(Settings.ACTION_SETTINGS)
            }
        } else {
            intent = Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        val settingPackage = "com.android.settings"
//        startApplication(settingPackage)
        device.wait(Until.hasObject(By.pkg(settingPackage)), 3000)
        var uiScroll = UiScrollable(UiSelector().scrollable(true))
//        uiScroll.scrollTextIntoView("Battery")
//        val item = device.findObject(By.text("Battery"))
//        item.click()
        val action = "Power saving mode"
        device.wait(Until.findObject(By.text(action)), 2000)
        val saving = device.findObject(By.text(action)).parent
        saving.parent.click()
        device.findObject(By.text(action)).parent.click()
    }

    @Test
    fun loginWifi() {
        // Open apps list by scrolling on home screen
        device.pressHome()
        val launcherPackage = device.currentPackageName
        val settingPackage = "com.android.settings"
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) // Clear out any previous instances
        context.startActivity(intent)
        device.wait<Boolean>(Until.hasObject(By.pkg(settingPackage)), 3000)
    }

    private fun startApplication(packageName: String) {
        try {
            val intent = Intent("android.intent.action.MAIN")
            intent.addCategory("android.intent.category.LAUNCHER")
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            val resolveInfoList: List<ResolveInfo> =
                context.packageManager.queryIntentActivities(intent, 0)
//            for (info in resolveInfoList) if (info.activityInfo.packageName.equals(packageName,
//                    ignoreCase = true)
//            ) {
            val info = resolveInfoList.first()
                launchComponent(info.activityInfo.packageName, info.activityInfo.name)
                return
//            }
            showInMarket(packageName)
        } catch (e: Exception) {
            showInMarket(packageName)
        }
    }

    private fun launchComponent(packageName: String, name: String) {
        val intent = Intent("android.intent.action.MAIN")
        intent.addCategory("android.intent.category.LAUNCHER")
        intent.component = ComponentName(packageName, name)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun showInMarket(packageName: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}
