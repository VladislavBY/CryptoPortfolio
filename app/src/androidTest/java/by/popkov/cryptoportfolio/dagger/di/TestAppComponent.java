package by.popkov.cryptoportfolio.dagger.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import by.popkov.cryptoportfolio.di.AppComponent;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {TestAppModule.class})
public interface TestAppComponent extends AppComponent {
    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context, @BindsInstance Application application);
    }
}
