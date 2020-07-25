package by.popkov.cryptoportfolio.dagger.di.fake;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.data_classes.PortfolioInfo;
import by.popkov.cryptoportfolio.domain.Coin;

public class FakePortfolioInfoMapper implements  Function<List<Coin>, PortfolioInfo> {
    @Inject
    public FakePortfolioInfoMapper() {
    }

    @Override
    public PortfolioInfo apply(List<Coin> coinList) {
        return null;
    }
}
