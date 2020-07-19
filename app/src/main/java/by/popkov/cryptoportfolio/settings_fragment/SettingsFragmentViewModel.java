package by.popkov.cryptoportfolio.settings_fragment;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;

@Singleton
class SettingsFragmentViewModel extends ViewModel {
    private SettingsRepository settingsRepository;

    @Inject
    SettingsFragmentViewModel(SettingsRepository settingsRepository) {
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
