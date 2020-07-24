package by.popkov.cryptoportfolio.dagger.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import by.popkov.cryptoportfolio.AppComponent;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {TestAppModule.class})
public interface TestAppComponent extends AppComponent {
}
