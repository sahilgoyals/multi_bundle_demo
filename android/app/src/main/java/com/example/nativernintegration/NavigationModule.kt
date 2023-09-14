package com.example.nativernintegration

import android.app.Activity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class NavigationModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return "Navigation"
    }

//    @ReactMethod
//    fun navigateTo(destination: String?) {
//        val callingIntent: Intent
//        callingIntent = when (destination) {
//            "NativeDemo" -> {
//                Log.d("Navigation", "Native Demo")
//                NativeDemo.getCallingIntent(reactContext)
//            }
//            else -> return
//        }
//        reactContext.startActivity(callingIntent)
//    }

    @ReactMethod
    fun goBack() {
        val currentActivity: Activity? = reactContext.currentActivity
        if (currentActivity != null) {
            currentActivity.finish()
        }
    }
}