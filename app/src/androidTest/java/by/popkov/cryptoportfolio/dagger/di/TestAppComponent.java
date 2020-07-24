package by.popkov.cryptoportfolio.dagger.di;

import javax.inject.Singleton;

import by.popkov.cryptoportfolio.AppComponent;
import dagger.Component;

@Singleton
@Component(modules = {TestAppModule.class})
public interface TestAppComponent extends AppComponent {
}
