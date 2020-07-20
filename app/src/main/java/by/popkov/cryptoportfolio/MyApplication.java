package by.popkov.cryptoportfolio;

import android.app.Application;

public class MyApplication extends Application {
    private static volatile AppComponent appComponent;

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            synchronized (this) {
                if (appComponent == null) {
                    appComponent = DaggerAppComponent.factory().create(getApplicationContext(), this);
                }
            }
        }
        return appComponent;
    }
}
