package by.popkov.cryptoportfolio.repositories.api_repository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import by.popkov.cryptoportfolio.domain.Coin;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRepositoryImp implements ApiRepository {
    public static final String USD = "USD";
    public static final String EUR = "EUR";
    public static final String RUB = "RUB";
    public static final String GBP = "GBP";
    public static final String JPY = "JPY";
    public static final String KRW = "KRW";
    public static final String BYN = "BYN";
    public static final String BTC = "BTC";
    public static final String ETH = "ETH";

    private static final String API_KEY = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=%s&tsyms=%s";
    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public @NonNull Observable<List<Coin>> getCoinsList(List<Coin> rawCoinList, String fiatSymbol) {
        final Request request = makeRequestFromList(rawCoinList, fiatSymbol);
        return Observable.create((ObservableOnSubscribe<Response>) emitter ->
                emitter.onNext(okHttpClient.newCall(request).execute()))
                .subscribeOn(Schedulers.io())
                .map(Response::body)
                .map(responseBody -> ConverterJsonToCoin.toCoinList(rawCoinList, fiatSymbol, responseBody.string()));
    }

    @Override
    public @NonNull Observable<Coin> getCoin(@NotNull Coin rawCoin, String fiatSymbol) {
        Request request = new Request.Builder()
                .url(String.format(API_KEY, rawCoin.getSymbol(), fiatSymbol))
                .build();
        return Observable.create((ObservableOnSubscribe<Response>) emitter ->
                emitter.onNext(okHttpClient.newCall(request).execute()))
                .subscribeOn(Schedulers.io())
                .map(Response::body)
                .map(responseBody -> ConverterJsonToCoin.toCoin(rawCoin, fiatSymbol, responseBody.string()));
    }

    @NotNull
    private Request makeRequestFromList(@NotNull List<Coin> rawCoinList, String fiatSymbol) {
        ArrayList<String> symbols = new ArrayList<>();
        rawCoinList.forEach(coin -> symbols.add(coin.getSymbol()));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < symbols.size(); i++) {
            if (i == 0) {
                stringBuilder.append(symbols.get(i));
            } else {
                stringBuilder.append(",").append(symbols.get(i));
            }
        }
        return new Request.Builder()
                .url(String.format(API_KEY, stringBuilder.toString(), fiatSymbol))
                .build();
    }
}
