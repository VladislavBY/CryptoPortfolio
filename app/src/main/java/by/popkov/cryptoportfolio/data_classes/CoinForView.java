package by.popkov.cryptoportfolio.data_classes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class CoinForView implements Serializable {
    @NonNull
    private String id;
    @NonNull
    private String symbol;
    @Nullable
    private String logoUrl;
    @Nullable
    private String fiatSymbol;
    @Nullable
    private String prise;
    @Nullable
    private String changePercent24Hour;
    @Nullable
    private String change24Hour;
    private int change24Color;
    @Nullable
    private String number;
    @Nullable
    private String sum;
    @Nullable
    private String changeSum24Hour;
    @Nullable
    private String globalSupply;
    @Nullable
    private String marketCap;
    @Nullable
    private String market24Volume;

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getSymbol() {
        return symbol;
    }

    @Nullable
    public String getLogoUrl() {
        return logoUrl;
    }

    @Nullable
    public String getFiatSymbol() {
        return fiatSymbol;
    }

    @Nullable
    public String getPrise() {
        return prise;
    }

    @Nullable
    public String getChangePercent24Hour() {
        return changePercent24Hour;
    }

    @Nullable
    public String getChange24Hour() {
        return change24Hour;
    }

    public int getChange24Color() {
        return change24Color;
    }

    @Nullable
    public String getNumber() {
        return number;
    }

    @Nullable
    public String getSum() {
        return sum;
    }

    @Nullable
    public String getChangeSum24Hour() {
        return changeSum24Hour;
    }


    @Nullable
    public String getGlobalSupply() {
        return globalSupply;
    }

    @Nullable
    public String getMarketCap() {
        return marketCap;
    }

    @Nullable
    public String getMarket24Volume() {
        return market24Volume;
    }

    private CoinForView(
            @NonNull String id,
            @NonNull String symbol,
            @Nullable String logoUrl,
            @Nullable String fiatSymbol,
            @Nullable String prise,
            @Nullable String changePercent24Hour,
            @Nullable String change24Hour,
            int change24Color,
            @Nullable String number,
            @Nullable String sum,
            @Nullable String changeSum24Hour,
            @Nullable String globalSupply,
            @Nullable String marketCap,
            @Nullable String market24Volume
    ) {
        this.id = id;
        this.symbol = symbol;
        this.logoUrl = logoUrl;
        this.fiatSymbol = fiatSymbol;
        this.prise = prise;
        this.changePercent24Hour = changePercent24Hour;
        this.change24Hour = change24Hour;
        this.change24Color = change24Color;
        this.number = number;
        this.sum = sum;
        this.changeSum24Hour = changeSum24Hour;
        this.globalSupply = globalSupply;
        this.marketCap = marketCap;
        this.market24Volume = market24Volume;
    }

    public static class Builder {
        @NonNull
        private String id;
        @NonNull
        private String symbol;
        @Nullable
        private String logoUrl;
        @Nullable
        private String fiatSymbol;
        @Nullable
        private String prise;
        @Nullable
        private String changePercent24Hour;
        @Nullable
        private String change24Hour;
        private int change24Color;
        @Nullable
        private String number;
        @Nullable
        private String sum;
        @Nullable
        private String changeSum24Hour;
        @Nullable
        private String globalSupply;
        @Nullable
        private String marketCap;
        @Nullable
        private String market24Volume;

        public Builder(@NonNull String id, @NonNull String symbol) {
            this.id = id;
            this.symbol = symbol;
        }

        public Builder setLogoUrl(@Nullable String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public Builder setFiatSymbol(@Nullable String fiatSymbol) {
            this.fiatSymbol = fiatSymbol;
            return this;
        }

        public Builder setPrise(@Nullable String prise) {
            this.prise = prise;
            return this;
        }

        public Builder setChangePercent24Hour(@Nullable String changePercent24Hour) {
            this.changePercent24Hour = changePercent24Hour;
            return this;
        }

        public Builder setChange24Hour(@Nullable String change24Hour) {
            this.change24Hour = change24Hour;
            return this;
        }

        public Builder setChange24Color(int change24Color) {
            this.change24Color = change24Color;
            return this;
        }

        public Builder setNumber(@Nullable String number) {
            this.number = number;
            return this;
        }

        public Builder setSum(@Nullable String sum) {
            this.sum = sum;
            return this;
        }

        public Builder setChangeSum24Hour(@Nullable String changeSum24Hour) {
            this.changeSum24Hour = changeSum24Hour;
            return this;
        }

        public Builder setGlobalSupply(@Nullable String globalSupply) {
            this.globalSupply = globalSupply;
            return this;
        }

        public Builder setMarketCap(@Nullable String marketCap) {
            this.marketCap = marketCap;
            return this;
        }

        public Builder setMarket24Volume(@Nullable String market24Volume) {
            this.market24Volume = market24Volume;
            return this;
        }

        public CoinForView build() {
            return new CoinForView(
                    id,
                    symbol,
                    logoUrl,
                    fiatSymbol,
                    prise,
                    changePercent24Hour,
                    change24Hour,
                    change24Color,
                    number,
                    sum,
                    changeSum24Hour,
                    globalSupply,
                    marketCap,
                    market24Volume
            );
        }
    }
}
