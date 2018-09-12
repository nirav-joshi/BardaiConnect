package demosample.nirav.com.application;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import javax.inject.Inject;

import demosample.nirav.com.data.DataManager;
import demosample.nirav.com.di.component.ApplicationComponent;
import demosample.nirav.com.di.component.DaggerApplicationComponent;
import demosample.nirav.com.di.module.ApplicationModule;


public class BardaiConnectApplication extends Application {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    DataManager dataManager;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity,
                                          Bundle savedInstanceState) {
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            @Override
            public void onActivityStarted(Activity activity) {/*Intentionally left blank*/}

            @Override
            public void onActivityResumed(Activity activity) {/*Intentionally left blank*/}

            @Override
            public void onActivityPaused(Activity activity) {/*Intentionally left blank*/}

            @Override
            public void onActivityStopped(Activity activity) {/*Intentionally left blank*/}

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {/*Intentionally left blank*/}

            @Override
            public void onActivityDestroyed(Activity activity) {/*Intentionally left blank*/}
        });
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}

