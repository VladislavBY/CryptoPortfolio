package by.popkov.cryptoportfolio.dagger.di.fake;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;

public class FakeSettingsRepository implements SettingsRepository {
    @Inject
    public FakeSettingsRepository() {
    }

    @Override
    public void saveFiatSetting(String fiatSymbol) {

    }

    @Override
    public String getFiatSetting() {
        return null;
    }

    @Override
    public void saveSortSetting(String sortType) {

    }

    @Override
    public String getSortSetting() {
        return null;
    }
}
