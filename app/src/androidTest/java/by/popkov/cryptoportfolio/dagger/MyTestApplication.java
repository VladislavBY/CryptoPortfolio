package by.popkov.cryptoportfolio.dagger;

import by.popkov.cryptoportfolio.AppComponent;
import by.popkov.cryptoportfolio.MyApplication;

public class MyTestApplication extends MyApplication {
    @Override
    protected AppComponent initializeComponent() {
        return DaggerTestAppComponent.factory.create();
    }
}
