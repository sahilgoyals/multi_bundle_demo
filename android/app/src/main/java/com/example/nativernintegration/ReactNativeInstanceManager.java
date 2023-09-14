package com.example.nativernintegration;

        import android.app.Activity;
        import android.app.Application;
        import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
        import com.facebook.react.PackageList;
        import com.facebook.react.ReactInstanceManager;
        import com.facebook.react.ReactPackage;
        import com.facebook.react.common.LifecycleState;
        import java.util.ArrayList;
        import java.util.Arrays;

public class ReactNativeInstanceManager {

    private static ReactNativeInstanceManager instance = new ReactNativeInstanceManager();

    public static ReactNativeInstanceManager getInstance() {
        return instance;
    }

    private ReactInstanceManager reactInstanceManager;

    public ReactInstanceManager GetReactInstanceManager()
    {
        return reactInstanceManager;
    }


    public ArrayList<ReactPackage> getOtherPackages() {
        return new ArrayList<>(Arrays.<ReactPackage>asList(
                new NavigationPackages()
        ));
    }

    public void createReactContextInBackground() {
        reactInstanceManager.createReactContextInBackground();
    }

    public ReactInstanceManager Rebuild(Application application, Activity activity) {
//        var b = intent.getExtras();
//        String keys = b.getString("key");
        String modulePath = "src/views/common/index.js";
//        String registry = "CalculatorRegistry";
//        if(keys == "news") {
//            modulePath = "src/views/News/NewsStack.js";
//            registry = "NewsRegistry";
//        }
//        val packages: List<ReactPackage> = PackageList(application).packages
        reactInstanceManager = ReactInstanceManager.builder()
                .setApplication(application)
//                .setCurrentActivity(activity)
                .setBundleAssetName("index.common.bundle")
                .setJSMainModulePath(modulePath)
//                .setJSBundleFile("assets://common.android.hermes.bundle")
                .addPackages(new PackageList(application).getPackages())
                .addPackages(getOtherPackages())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setJavaScriptExecutorFactory(new HermesExecutorFactory())
                .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                .build();

//        if (!reactInstanceManager.hasStartedCreatingInitialContext())
//        {
//            reactInstanceManager.createReactContextInBackground();
//        }

//        reactInstanceManager.b

        return reactInstanceManager;
    }





}
