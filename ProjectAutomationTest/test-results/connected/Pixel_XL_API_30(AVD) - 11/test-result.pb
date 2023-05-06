
å
n
ExampleInstrumentedTest-com.livewallpaper.video.projectautomationtest	loginWifi2¬Ø÷ùÄå˚):ÀØ÷ù¿Î≥‰®
√java.lang.SecurityException: Permission Denial: package=com.livewallpaper.video.projectautomationtest.test does not belong to uid=10180
at android.os.Parcel.createExceptionOrNull(Parcel.java:2373)
at android.os.Parcel.createException(Parcel.java:2357)
at android.os.Parcel.readException(Parcel.java:2340)
at android.os.Parcel.readException(Parcel.java:2282)
at android.app.IActivityTaskManager$Stub$Proxy.startActivity(IActivityTaskManager.java:3696)
at android.app.Instrumentation.execStartActivity(Instrumentation.java:1723)
at androidx.test.runner.MonitoringInstrumentation.execStartActivity(MonitoringInstrumentation.java:605)
at android.app.ContextImpl.startActivity(ContextImpl.java:1023)
at android.app.ContextImpl.startActivity(ContextImpl.java:994)
at com.livewallpaper.video.projectautomationtest.ExampleInstrumentedTest.loginWifi(ExampleInstrumentedTest.kt:137)
... 34 trimmed
Caused by: android.os.RemoteException: Remote stack trace:
at com.android.server.wm.ActivityTaskManagerService.assertPackageMatchesCallingUid(ActivityTaskManagerService.java:2598)
at com.android.server.wm.ActivityTaskManagerService.startActivityAsUser(ActivityTaskManagerService.java:1081)
at com.android.server.wm.ActivityTaskManagerService.startActivityAsUser(ActivityTaskManagerService.java:1072)
at com.android.server.wm.ActivityTaskManagerService.startActivity(ActivityTaskManagerService.java:1047)
at android.app.IActivityTaskManager$Stub.onTransact(IActivityTaskManager.java:1422)android.os.RemoteException√java.lang.SecurityException: Permission Denial: package=com.livewallpaper.video.projectautomationtest.test does not belong to uid=10180
at android.os.Parcel.createExceptionOrNull(Parcel.java:2373)
at android.os.Parcel.createException(Parcel.java:2357)
at android.os.Parcel.readException(Parcel.java:2340)
at android.os.Parcel.readException(Parcel.java:2282)
at android.app.IActivityTaskManager$Stub$Proxy.startActivity(IActivityTaskManager.java:3696)
at android.app.Instrumentation.execStartActivity(Instrumentation.java:1723)
at androidx.test.runner.MonitoringInstrumentation.execStartActivity(MonitoringInstrumentation.java:605)
at android.app.ContextImpl.startActivity(ContextImpl.java:1023)
at android.app.ContextImpl.startActivity(ContextImpl.java:994)
at com.livewallpaper.video.projectautomationtest.ExampleInstrumentedTest.loginWifi(ExampleInstrumentedTest.kt:137)
... 34 trimmed
Caused by: android.os.RemoteException: Remote stack trace:
at com.android.server.wm.ActivityTaskManagerService.assertPackageMatchesCallingUid(ActivityTaskManagerService.java:2598)
at com.android.server.wm.ActivityTaskManagerService.startActivityAsUser(ActivityTaskManagerService.java:1081)
at com.android.server.wm.ActivityTaskManagerService.startActivityAsUser(ActivityTaskManagerService.java:1072)
at com.android.server.wm.ActivityTaskManagerService.startActivity(ActivityTaskManagerService.java:1047)
at android.app.IActivityTaskManager$Stub.onTransact(IActivityTaskManager.java:1422)"œ

logcatandroidπ
∂/Users/loannth5/code/ProjectAutomationTest/test-results/connected/Pixel_XL_API_30(AVD) - 11/logcat-com.livewallpaper.video.projectautomationtest.ExampleInstrumentedTest-loginWifi.txt"Ü

device-infoandroidl
j/Users/loannth5/code/ProjectAutomationTest/test-results/connected/Pixel_XL_API_30(AVD) - 11/device-info.pb"á

device-info.meminfoandroide
c/Users/loannth5/code/ProjectAutomationTest/test-results/connected/Pixel_XL_API_30(AVD) - 11/meminfo"á

device-info.cpuinfoandroide
c/Users/loannth5/code/ProjectAutomationTest/test-results/connected/Pixel_XL_API_30(AVD) - 11/cpuinfo*Î
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriverv
t/Users/loannth5/code/ProjectAutomationTest/test-results/connected/Pixel_XL_API_30(AVD) - 11/testlog/test-results.log 2
text/plain