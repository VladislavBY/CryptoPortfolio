package by.popkov.cryptoportfolio.repositories.database_repository;


import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Future;

import by.popkov.cryptoportfolio.domain.Coin;
import io.reactivex.Observable;

public interface DatabaseRepository {
    LiveData<List<Coin>> getCoinListLiveData();
    Future<List<Coin>> getCoinListFuture();
    Observable<List<Coin>> getCoinListObservable();
    LiveData<Coin> getCoinLiveData(String id);
    Future <Coin> getCoin(String id);
    Observable<Coin> getCoinObservable(String id);
    void addNewCoin(final Coin coin);
    void deleteCoin(final Coin coin);
    void updateCoin(final Coin coin);
}
