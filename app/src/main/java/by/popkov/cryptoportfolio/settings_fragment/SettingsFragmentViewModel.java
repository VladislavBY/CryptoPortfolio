package by.popkov.cryptoportfolio.settings_fragment;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.popkov.cryptoportfolio.MyApplication;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;

@Singleton
public class SettingsFragmentViewModel extends AndroidViewModel {
    @Inject
    SettingsRepository settingsRepository;

    @Inject
    SettingsFragmentViewModel(Application application) {
        super(application);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
    }

    String getFiatSettings() {
        return settingsRepository.getFiatSetting();
    }

    void saveFiatSetting(String fiatSymbol) {
        settingsRepository.saveFiatSetting(fiatSymbol);
    }

    String getSortSettings() {
        return settingsRepository.getSortSetting();
    }

    void saveSortSetting(String sortType) {
        settingsRepository.saveSortSetting(sortType);
    }
}
