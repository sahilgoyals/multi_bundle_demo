package com.example.nativernintegration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.facebook.react.*
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader
import java.lang.reflect.Field
import java.util.*


var appACatalst: CatalystInstance? = null
var appBCatalst: CatalystInstance? = null

class RActivity : ReactActivity(), DefaultHardwareBackBtnHandler {
    private lateinit var reactRootView: ReactRootView
    private var reactInstanceManager: ReactInstanceManager? = null
    var reactContext: ReactContext? = null

    fun getLaunchOptions(): Bundle {
//        val imageList = arrayListOf("http://foo.com/bar1.png", "http://foo.com/bar2.png")
        val initialProperties = Bundle().apply {

        }
        return initialProperties
    }

    private fun getPackages(key: String?): ArrayList<ReactPackage>? {
        val deploymentKey = "CodePushDeploymentKey_$key";
        val resourceField: Field = R.string::class.java.getDeclaredField(deploymentKey);
        val resourceId = resourceField.getInt(resourceField);
        Log.v("resourceIdresourceId", resourceId.toString())
        return ArrayList<ReactPackage>(
            Arrays.asList<ReactPackage>(
                NavigationPackages(),
//                CodePush(
//                    resources.getString(resourceId),
//                    applicationContext, BuildConfig.DEBUG,
//                ),
                )
        )
    }

    fun getOtherPackages(): ArrayList<ReactPackage> {
        return ArrayList(
            Arrays.asList<ReactPackage>(
                NavigationPackages()
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        SoLoader.init(this, false)
        val b = intent.extras
        val keys = b?.getString("key")
        var modulePath = "src/views/appB"
        var registry = "appBRegistry"
        if(keys == "appA") {
            modulePath = "src/views/appB"
            registry = "appARegistry"
        }
        val date = Date()
        val timeMilli = date.time
        Log.v("onCreate called", timeMilli.toString())
//        reactRootView = ReactRootView(this)
//        val deploymentKey = "CodePushDeploymentKey_$keys";
//        val resourceField: Field = R.string::class.java.getDeclaredField(deploymentKey);
//        val resourceId = resourceField.getInt(resourceField);
//        CodePushUpdateManager.setmDeploymentKey(resources.getString(resourceId))
//        val packages: List<ReactPackage> = PackageList(application).packages
//        packages.toMutableList().add(NavigationPackages())
//        // Packages that cannot be autolinked yet can be added manually here, for example:
////         packages.add(MyReactNativePackage())
//        // Remember to include them in `settings.gradle` and `app/build.gradle` too.
////        Log.v("reactInstanceManager val", reactInstanceManager.toString())
//            Log.v("packageFilePath 0", "index.${keys}.bundle")
//            Log.v("packageFilePath 4", "${CodePush.getJSBundleFile("index.${keys}.bundle")}")
//            reactInstanceManager = ReactInstanceManager.builder()
//                .addPackages(getPackages(keys))
//                .setApplication(application)
//                .setCurrentActivity(this)
////                .setBundleAssetName("index.${keys}.bundle")
//                .addPackages(packages)
//                .setJSMainModulePath(modulePath)
//                .setUseDeveloperSupport(BuildConfig.DEBUG)
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .setJavaScriptExecutorFactory(HermesExecutorFactory())
//                .setJSBundleFile(CodePush.getJSBundleFile("index.${keys}.bundle"))
//                .build()
//        // The string here (e.g. "MyReactNativeApp") has to match
//        // the string in AppRegistry.registerComponent() in index.js
//        reactRootView?.startReactApplication(reactInstanceManager, registry, null)
//        setContentView(reactRootView)
//        bootCommonRnBundle()

        reactRootView = ReactRootView(this)

        // Boot business Javascript bundle
        val reactNativeInstanceWrapper = ReactNativeInstanceManager.getInstance()
        reactInstanceManager = reactNativeInstanceWrapper.GetReactInstanceManager()
        Log.v("inside if is called", "inside")
        if(reactInstanceManager!!.hasStartedCreatingInitialContext()) {
            reactContext = reactInstanceManager!!.getCurrentReactContext()
            Log.v("inside if is called", "inside")
            try {

//                val catalystInstanceBuilder: CatalystInstanceImpl.Builder =
//                    CatalystInstanceImpl.Builder()
//                        .setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault())
//                        .setRegistry(keys)
//                        .setJSBundleLoader(jsBundleLoader)
                    if(keys == "appA") {
                        appACatalst = reactContext?.catalystInstance
//                        newsCatalst?.setSourceURLs("assets://index.${keys}.bundle", "assets://index.${keys}.bundle")
                        (appACatalst as CatalystInstanceImpl).loadScriptFromAssets(
                            reactContext?.assets,
                            "assets://index.${keys}.bundle",
                            true
                        )
                        reactRootView?.startReactApplication(
                            reactInstanceManager,
                            "appARegistry",
                            null
                        )
                        setContentView(reactRootView)
                    } else {
                        appBCatalst = reactContext?.catalystInstance
                        (appBCatalst as CatalystInstanceImpl).loadScriptFromAssets(
                            reactContext?.assets,
                            "assets://index.${keys}.bundle",
                            true
                        )
//                        calculatorcatalyst?.runJSBundle()
                        Log.v("source url", appBCatalst?.getSourceURL().toString())
                        reactRootView?.startReactApplication(
                            reactInstanceManager,
                            "appBRegistry",
                            null
                        )
                        setContentView(reactRootView)
                    }



//                    reactRootView?.startReactApplication(
//                        reactInstanceManager,
//                        registry,
//                        null
//                    )
//                    setContentView(reactRootView)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
        }



    }
    private fun bootCommonRnBundle() {
        Log.v("bootCommonRnBundle called", "called")
        if(ReactNativeInstanceManager.getInstance().GetReactInstanceManager() == null) {
//            val reactNativeInstanceWrapper = ReactNativeInstanceManager.getInstance()
//            reactNativeInstanceWrapper.Rebuild( this)
//            reactNativeInstanceWrapper.createReactContextInBackground();
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onDestroy() {
        Log.v("onDestroy rohit", "onDestroy")
        super.onDestroy()
        if (reactInstanceManager != null) {
            reactInstanceManager?.onHostDestroy(this);
        }
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();

        }
//        reactContext = null
        Log.v("onDestroy rohit 2", "onDestroy")

//        calculatorcatalyst?.destroy()
//        newsCatalst?.destroy()
    }

    override fun onResume() {
        Log.v("onResume is called", "onResumerohit")
        super.onResume()
        reactInstanceManager?.onHostResume(this, this)
    }
    override fun onPause() {
        super.onPause()
        if (reactInstanceManager != null) {
            reactInstanceManager?.onHostPause(this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun invokeDefaultOnBackPressed() {
        reactInstanceManager?.onBackPressed()
        super.onBackPressed()
    }
}



