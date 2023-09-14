package com.example.nativernintegration

import android.app.Activity
import android.app.Application
import com.facebook.react.*
import com.facebook.react.BuildConfig
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.soloader.SoLoader


class MyReactApplication : Application(), ReactApplication {
    private var mCurrentActivity: Activity? = null


    override fun onCreate() {
        super.onCreate()
        mCurrentActivity = null
        SoLoader.init(this, false)
        val reactNativeInstanceWrapper = ReactNativeInstanceManager.getInstance()
        reactNativeInstanceWrapper.Rebuild(this, mCurrentActivity)
        //        String registry = "CalculatorRegistry";
//        if(keys == "news") {
//            modulePath = "src/views/News/NewsStack.js";
//            registry = "NewsRegistry";
//        }
//        val packages: List<ReactPackage> = PackageList(application).packages
        reactNativeInstanceWrapper.createReactContextInBackground();
    }


    private val reactNativeHost =
        object : DefaultReactNativeHost(this) {
            override fun getUseDeveloperSupport() = BuildConfig.DEBUG


            override fun getPackages(): List<ReactPackage> {
                val packages = PackageList(this).getPackages().toMutableList()
                // Packages that cannot be autolinked yet can be added manually here
                return packages
            }


        }
    override fun getReactNativeHost(): ReactNativeHost = reactNativeHost


}