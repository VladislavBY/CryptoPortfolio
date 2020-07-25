package by.popkov.cryptoportfolio.dagger.di.fake;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.domain.Coin;
import by.popkov.cryptoportfolio.repositories.database_repository.DatabaseRepository;
import io.reactivex.Observable;
import io.reactivex.Single;

public class FakeDatabaseRepository implements DatabaseRepository {
    @Inject
    public FakeDatabaseRepository() {
    }

    @Override
    public LiveData<List<Coin>> getCoinListLiveData() {
        return null;
    }

    @Override
    public Future<List<Coin>> getCoinListFuture() {
        return null;
    }

    @Override
    public Observable<List<Coin>> getCoinListObservable() {
        return null;
    }

    @Override
    public Single<List<Coin>> getCoinListSingle() {
        return null;
    }

    @Override
    public LiveData<Coin> getCoinLiveData(String id) {
        return null;
    }

    @Override
    public Future<Coin> getCoin(String id) {
        return null;
    }

    @Override
    public Observable<Coin> getCoinObservable(String id) {
        return null;
    }

    @Override
    public Single<Coin> getCoinSingle(String id) {
        return null;
    }

    @Override
    public void addNewCoin(Coin coin) {

    }

    @Override
    public void deleteCoin(Coin coin) {

    }

    @Override
    public void updateCoin(Coin coin) {

    }
}
