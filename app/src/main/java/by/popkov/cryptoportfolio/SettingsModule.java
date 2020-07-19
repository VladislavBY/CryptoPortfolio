package by.popkov.cryptoportfolio;

import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp;
import dagger.Binds;
import dagger.Module;

@Module
abstract class SettingsModule {
    @Binds
    abstract SettingsRepository provide(SettingsRepositoryImp settingsRepositoryImp);
}
