package by.popkov.cryptoportfolio.dagger.di.fake;

import java.util.List;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.domain.Coin;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepository;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class FakeApiRepository implements ApiRepository {
    @Inject
    public FakeApiRepository() {
    }

    @Override
    public @NonNull Observable<List<Coin>> getCoinsList(List<Coin> rawCoinList, String fiatSymbol) {
        return null;
    }

    @Override
    public @NonNull Observable<Coin> getCoin(Coin rawCoin, String fiatSymbol) {
        return null;
    }
}
