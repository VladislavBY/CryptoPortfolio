package by.popkov.cryptoportfolio.dagger;

import by.popkov.cryptoportfolio.AppComponent;
import by.popkov.cryptoportfolio.MyApplication;
import by.popkov.cryptoportfolio.dagger.di.DaggerTestAppComponent;

public class MyTestApplication extends MyApplication {
    @Override
    protected AppComponent initializeComponent() {
        return DaggerTestAppComponent.factory().create(getApplicationContext(), this);
    }
}
