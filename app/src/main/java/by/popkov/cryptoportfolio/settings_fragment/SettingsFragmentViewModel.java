package by.popkov.cryptoportfolio.settings_fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;

@Singleton
public class SettingsFragmentViewModel extends AndroidViewModel {
    private SettingsRepository settingsRepository;

    @Inject
    SettingsFragmentViewModel(@NonNull Application application, SettingsRepository settingsRepository) {
        super(application);
        this.settingsRepository = settingsRepository;
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
