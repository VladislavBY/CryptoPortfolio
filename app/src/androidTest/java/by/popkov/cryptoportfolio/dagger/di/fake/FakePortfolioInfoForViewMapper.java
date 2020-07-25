package by.popkov.cryptoportfolio.dagger.di.fake;

import java.util.function.Function;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.data_classes.PortfolioInfo;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfoForView;

public class FakePortfolioInfoForViewMapper implements Function<PortfolioInfo, PortfolioInfoForView> {
    @Inject
    public FakePortfolioInfoForViewMapper() {
    }

    @Override
    public PortfolioInfoForView apply(PortfolioInfo portfolioInfo) {
        return null;
    }
}
