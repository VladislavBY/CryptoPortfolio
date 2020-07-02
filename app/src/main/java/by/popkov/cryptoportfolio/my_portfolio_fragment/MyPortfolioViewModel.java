package by.popkov.cryptoportfolio.my_portfolio_fragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import by.popkov.cryptoportfolio.data_classes.CoinForView;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfo;
import by.popkov.cryptoportfolio.data_classes.PortfolioInfoForView;
import by.popkov.cryptoportfolio.domain.Coin;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepository;
import by.popkov.cryptoportfolio.repositories.database_repository.DatabaseRepository;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import static by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp.ALPHABET_SORT;
import static by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp.SUM_SORT;
import static by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp.TIME_ADD_SORT;

class MyPortfolioViewModel extends AndroidViewModel {
    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;
    private SettingsRepository settingsRepository;
    private Function<Coin, CoinForView> coinForViewMapper;
    private Function<List<Coin>, PortfolioInfo> portfolioInfoMapper;
    private Function<PortfolioInfo, PortfolioInfoForView> portfolioInfoForViewMapper;
    private MutableLiveData<List<CoinForView>> coinForViewListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<PortfolioInfoForView> portfolioInfoForViewMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> searchViewQueryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingMutableLiveData = new MutableLiveData<>();

    MyPortfolioViewModel(
            Application application,
            ApiRepository apiRepository,
            DatabaseRepository databaseRepository,
            SettingsRepository settingsRepository,
            Function<Coin, CoinForView> coinForViewMapper,
            Function<List<Coin>, PortfolioInfo> portfolioInfoMapper,
            Function<PortfolioInfo, PortfolioInfoForView> portfolioInfoForViewMapper
    ) {
        super(application);
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
        this.settingsRepository = settingsRepository;
        this.coinForViewMapper = coinForViewMapper;
        this.portfolioInfoMapper = portfolioInfoMapper;
        this.portfolioInfoForViewMapper = portfolioInfoForViewMapper;
        connectToRepo();
    }

    LiveData<Boolean> getIsLoadingLiveData() {
        return isLoadingMutableLiveData;
    }

    private void setIsLoadingLiveData(Boolean isLoading) {
        this.isLoadingMutableLiveData.setValue(isLoading);
    }

    LiveData<String> getSearchViewQueryViewLiveData() {
        return searchViewQueryMutableLiveData;
    }

    void setValueSearchViewQueryLiveData(String queryText) {
        this.searchViewQueryMutableLiveData.setValue(queryText);
    }

    LiveData<List<CoinForView>> getCoinForViewListLiveData() {
        return coinForViewListMutableLiveData;
    }

    LiveData<PortfolioInfoForView> getPortfolioInfoForViewLiveData() {
        return portfolioInfoForViewMutableLiveData;
    }

    @SuppressLint("CheckResult")
    void updatePortfolioData() {
        setIsLoadingLiveData(true);
        databaseRepository.getCoinListSingle()
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(rawCoinList -> apiRepository.getCoinsList(rawCoinList, settingsRepository.getFiatSetting())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::onNextCoinList, this::onError),
                        this::onError);
    }

    @SuppressLint("CheckResult")
    private void connectToRepo() {
        setIsLoadingLiveData(true);
        databaseRepository.getCoinListObservable()
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(rawCoinList ->
                                apiRepository.getCoinsList(rawCoinList, settingsRepository.getFiatSetting())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(this::onNextCoinList, this::onError),
                        this::onError);
    }


    private void onNextCoinList(List<Coin> coinList) {
        setIsLoadingLiveData(false);
        setCoinForViewListLiveData(coinList);
        setPortfolioInfoForViewMutableLiveData(coinList);
    }

    private void onError(@NotNull Throwable throwable) {
        setIsLoadingLiveData(false);
        showThrowable(throwable);
    }

    private void showThrowable(@NotNull Throwable throwable) {
        Toast.makeText(getApplication(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    private void setCoinForViewListLiveData(@NotNull List<Coin> coinList) {
        coinForViewListMutableLiveData
                .setValue(
                        coinList.stream()
                                .sorted(getComparator(settingsRepository.getSortSetting()))
                                .map(coinForViewMapper)
                                .collect(Collectors.toList())
                );
    }

    private Comparator<? super Coin> getComparator(@NotNull String sortSetting) {
        switch (sortSetting) {
            case ALPHABET_SORT:
                return (Comparator<Coin>) (o1, o2) -> o1.getSymbol().compareToIgnoreCase(o2.getSymbol());
            case SUM_SORT:
                return (Comparator<Coin>) (o1, o2) -> {
                    if (o1.getPrise() != null && o2.getPrise() != null) {
                        Double sumOne = o1.getPrise() * o1.getNumber();
                        Double sumTwo = o2.getPrise() * o2.getNumber();
                        return sumTwo.compareTo(sumOne);
                    } else return 0;
                };
            case TIME_ADD_SORT:
            default:
                return (Comparator<Coin>) (o1, o2) -> 0;
        }
    }

    private void setPortfolioInfoForViewMutableLiveData(List<Coin> coinList) {
        portfolioInfoForViewMutableLiveData
                .setValue(portfolioInfoForViewMapper.apply(portfolioInfoMapper.apply(coinList)));
    }
}