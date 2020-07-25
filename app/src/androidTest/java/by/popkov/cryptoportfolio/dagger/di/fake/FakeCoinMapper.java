package by.popkov.cryptoportfolio.dagger.di.fake;

import java.util.function.Function;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.domain.Coin;
import by.popkov.cryptoportfolio.repositories.database_repository.database.CoinEntity;

public class FakeCoinMapper implements Function<CoinEntity, Coin> {
    @Inject
    public FakeCoinMapper() {
    }

    @Override
    public Coin apply(CoinEntity coinEntity) {
        return null;
    }
}
