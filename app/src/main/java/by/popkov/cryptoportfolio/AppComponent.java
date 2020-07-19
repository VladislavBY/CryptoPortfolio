package by.popkov.cryptoportfolio;

import android.content.Context;

import javax.inject.Singleton;

import by.popkov.cryptoportfolio.coin_info_fragment.CoinInfoFragment;
import by.popkov.cryptoportfolio.my_portfolio_fragment.MyPortfolioFragment;
import by.popkov.cryptoportfolio.settings_fragment.SettingsFragment;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {SettingsModule.class})
public interface AppComponent {
    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }

    void inject(MainActivity mainActivity);

    void inject(MyPortfolioFragment myPortfolioFragment);

    void inject(CoinInfoFragment coinInfoFragment);

    void inject(SettingsFragment settingsFragment);

}
