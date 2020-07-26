package by.popkov.cryptoportfolio;

import android.app.Application;

import by.popkov.cryptoportfolio.di.AppComponent;
import by.popkov.cryptoportfolio.di.DaggerAppComponent;

public class MyApplication extends Application {
    private static volatile AppComponent appComponent;

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            synchronized (this) {
                if (appComponent == null) {
                    appComponent = initializeComponent();
                }
            }
        }
        return appComponent;
    }

    protected AppComponent initializeComponent() {
        return DaggerAppComponent.factory().create(getApplicationContext(), this);
    }
}
