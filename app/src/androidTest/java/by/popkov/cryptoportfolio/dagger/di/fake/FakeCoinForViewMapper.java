package by.popkov.cryptoportfolio.dagger.di.fake;

import java.util.function.Function;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.data_classes.CoinForView;
import by.popkov.cryptoportfolio.domain.Coin;

public class FakeCoinForViewMapper implements Function<Coin, CoinForView> {
    @Inject
    public FakeCoinForViewMapper() {
    }

    @Override
    public CoinForView apply(Coin coin) {
        return null;
    }
}
