package by.popkov.cryptoportfolio.dagger.di;

import java.util.List;
import java.util.function.Function;

import by.popkov.cryptoportfolio.dagger.di.fake.FakeApiRepository;
import by.popkov.cryptoportfolio.dagger.di.fake.FakeCoinForViewMapper;
import by.popkov.cryptoportfolio.dagger.di.fake.FakeCoinMapper;
import by.popkov.cryptoportfolio.dagger.di.fake.FakeDatabaseRepository;
import by.popkov.cryptoportfolio.dagger.di.fake.FakePortfolioInfoForViewMapper;
import by.popkov.cryptoportfolio.dagger.di.fake.FakePortfolioInfoMapper;
import by.popkov.cryptoportfolio.dagger.di.fake.FakeSettingsRepository;
import by.popkov.cryptoportfolio.data_classes.CoinForView;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfo;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfoForView;
import by.popkov.cryptoportfolio.domain.Coin;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepository;
import by.popkov.cryptoportfolio.repositories.database_repository.DatabaseRepository;
import by.popkov.cryptoportfolio.repositories.database_repository.database.CoinEntity;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestAppModule {
    @Binds
    abstract SettingsRepository provideSettingsRepository(FakeSettingsRepository f);

    @Binds
    abstract ApiRepository provideApiRepository(FakeApiRepository f);

    @Binds
    abstract DatabaseRepository provideDatabaseRepository(FakeDatabaseRepository f);

    @Binds
    abstract Function<CoinEntity, Coin> provideCoinMapper(FakeCoinMapper f);

    @Binds
    abstract Function<Coin, CoinForView> provideCoinForViewMapper(FakeCoinForViewMapper f);

    @Binds
    abstract Function<List<Coin>, PortfolioInfo> providePortfolioInfoMapper(FakePortfolioInfoMapper f);

    @Binds
    abstract Function<PortfolioInfo, PortfolioInfoForView> providePortfolioInfoForViewMapper(FakePortfolioInfoForViewMapper f);

}
