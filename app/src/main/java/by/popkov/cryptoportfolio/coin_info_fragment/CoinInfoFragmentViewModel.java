package by.popkov.cryptoportfolio.coin_info_fragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.popkov.cryptoportfolio.data_classes.CoinForView;
import by.popkov.cryptoportfolio.domain.Coin;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepository;
import by.popkov.cryptoportfolio.repositories.database_repository.DatabaseRepository;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

@Singleton
public class CoinInfoFragmentViewModel extends AndroidViewModel {
    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;
    private SettingsRepository settingsRepository;
    private Function<Coin, CoinForView> mapper;
    private CoinForView coinForView;
    private MutableLiveData<CoinForView> coinForViewMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingMutableLiveData = new MutableLiveData<>();
    private Disposable subscribeOnCoin;

    void setCoinForView(CoinForView coinForView) {
        if (this.coinForView == null) {
            this.coinForView = coinForView;
            connectToRepo();
        } else if (!this.coinForView.getId().equals(coinForView.getId())) {
            if (!subscribeOnCoin.isDisposed()) {
                subscribeOnCoin.dispose();
                this.coinForView = coinForView;
                connectToRepo();
            }
        }
    }

    @Inject
    CoinInfoFragmentViewModel(
            @NonNull Application application,
            ApiRepository apiRepository,
            DatabaseRepository databaseRepository,
            SettingsRepository settingsRepository,
            Function<Coin, CoinForView> mapper
    ) {
        super(application);
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
        this.settingsRepository = settingsRepository;
        this.mapper = mapper;
    }

    LiveData<Boolean> getIsLoadingLiveData() {
        return isLoadingMutableLiveData;
    }

    private void setIsLoadingLiveData(Boolean isLoading) {
        this.isLoadingMutableLiveData.setValue(isLoading);
    }

    private void setCoinForViewMutableLiveData(CoinForView coinForView) {
        coinForViewMutableLiveData.setValue(coinForView);
    }

    LiveData<CoinForView> getCoinForViewLiveData() {
        return coinForViewMutableLiveData;
    }

    @SuppressLint("CheckResult")
    void refreshCoinData() {
        setIsLoadingLiveData(true);
        databaseRepository.getCoinSingle(coinForView.getId())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(rawCoin -> apiRepository.getCoin(rawCoin, settingsRepository.getFiatSetting())
                                .observeOn(AndroidSchedulers.mainThread())
                                .map(coin -> mapper.apply(coin))
                                .subscribe(this::onNextCoinForView, this::onError),
                        this::onError);
    }

    @SuppressLint("CheckResult")
    private void connectToRepo() {
        setIsLoadingLiveData(true);
        subscribeOnCoin = databaseRepository.getCoinObservable(coinForView.getId()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(
                        coin -> apiRepository.getCoin(coin, settingsRepository.getFiatSetting())
                                .map(coin1 -> mapper.apply(coin1))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::onNextCoinForView, this::onError),
                        this::onError
                );
    }

    private void onNextCoinForView(CoinForView coinForView) {
        setCoinForViewMutableLiveData(coinForView);
        setIsLoadingLiveData(false);
    }

    private void onError(@NotNull Throwable throwable) {
        setIsLoadingLiveData(false);
        showThrowable(throwable);
    }

    private void showThrowable(@NotNull Throwable throwable) {
        Toast.makeText(getApplication(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    void updateCoin(Double number) {
        databaseRepository.updateCoin(new Coin.Builder(coinForView.getId(), coinForView.getSymbol(), number).build());
    }

    void deleteCoin() {
        databaseRepository.deleteCoin(new Coin.Builder(coinForView.getId(), coinForView.getSymbol(), 0.0).build());
    }
}
