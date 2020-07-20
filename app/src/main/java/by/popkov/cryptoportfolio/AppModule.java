package by.popkov.cryptoportfolio;

import java.util.List;
import java.util.function.Function;

import by.popkov.cryptoportfolio.data_classes.CoinForView;
import by.popkov.cryptoportfolio.data_classes.CoinForViewMapper;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfo;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfoForView;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfoForViewMapper;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfoMapper;
import by.popkov.cryptoportfolio.domain.Coin;
import by.popkov.cryptoportfolio.domain.CoinMapper;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepository;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp;
import by.popkov.cryptoportfolio.repositories.database_repository.DatabaseRepository;
import by.popkov.cryptoportfolio.repositories.database_repository.DatabaseRepositoryImp;
import by.popkov.cryptoportfolio.repositories.database_repository.database.CoinEntity;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp;
import dagger.Binds;
import dagger.Module;

@Module
abstract class AppModule {
    @Binds
    abstract SettingsRepository provideSettingsRepository(SettingsRepositoryImp settingsRepositoryImp);

    @Binds
    abstract ApiRepository provideApiRepository(ApiRepositoryImp apiRepositoryImp);

    @Binds
    abstract DatabaseRepository provideDatabaseRepository(DatabaseRepositoryImp databaseRepositoryImp);

    @Binds
    abstract Function<CoinEntity, Coin> provideCoinMapper(CoinMapper coinMapper);

    @Binds
    abstract Function<Coin, CoinForView> provideCoinForViewMapper(CoinForViewMapper coinForViewMapper);

    @Binds
    abstract Function<List<Coin>, PortfolioInfo> providePortfolioInfoMapper(PortfolioInfoMapper portfolioInfoMapper);

    @Binds
    abstract Function<PortfolioInfo, PortfolioInfoForView> providePortfolioInfoForViewMapper(PortfolioInfoForViewMapper portfolioInfoForViewMapper);

}
