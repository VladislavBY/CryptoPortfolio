package by.popkov.cryptoportfolio;

import android.app.Application;

import dagger.Component;

public class MyApplication extends Application {
    private static volatile AppComponent appComponent;

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            synchronized (this) {
                if (appComponent == null) {
                    appComponent = DaggerAppComponent.factory().create(getApplicationContext());
                }
            }
        }
        return appComponent;
    }
}
