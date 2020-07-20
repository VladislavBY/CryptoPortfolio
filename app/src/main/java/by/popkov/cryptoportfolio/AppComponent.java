package by.popkov.cryptoportfolio;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import by.popkov.cryptoportfolio.add_new_coin_dialog_fragment.AddNewCoinDialogFragment;
import by.popkov.cryptoportfolio.add_new_coin_dialog_fragment.AddNewCoinDialogFragmentViewModel;
import by.popkov.cryptoportfolio.coin_info_fragment.CoinInfoFragment;
import by.popkov.cryptoportfolio.coin_info_fragment.CoinInfoFragmentViewModel;
import by.popkov.cryptoportfolio.my_portfolio_fragment.MyPortfolioFragment;
import by.popkov.cryptoportfolio.my_portfolio_fragment.MyPortfolioViewModel;
import by.popkov.cryptoportfolio.settings_fragment.SettingsFragment;
import by.popkov.cryptoportfolio.settings_fragment.SettingsFragmentViewModel;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context, @BindsInstance Application application);
    }

    void inject(MyPortfolioFragment myPortfolioFragment);

    void inject(CoinInfoFragment coinInfoFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(AddNewCoinDialogFragment addNewCoinDialogFragment);

    void inject(MyPortfolioViewModel myPortfolioViewModel);

    void inject(AddNewCoinDialogFragmentViewModel addNewCoinDialogFragmentViewModel);

    void inject(CoinInfoFragmentViewModel coinInfoFragmentViewModel);

    void inject(SettingsFragmentViewModel settingsFragmentViewModel);
}
