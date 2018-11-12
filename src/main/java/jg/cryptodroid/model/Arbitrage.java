package jg.cryptodroid.model;

import jg.cryptodroid.enums.CoinList;
import jg.cryptodroid.inmemorydatabase.ExchangeBase;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Arbitrage {
    private String marketFrom;
    private String marketTo;
    private String coinTag;
    private double buyPrice;
    private double buyVolume;
    private double sellVolume;
    private double buyFee;
    private double withdrawalFee;
    private double sellFee;
    private double sellPrice;
    private double earnPercentage;
    private long lastUpdate;

    public Arbitrage(String marketFrom, String marketTo, String coinTag, double buyPrice, double sellPrice, double buyVolume, double sellVolume, Long lastUpdate) {
        this.marketFrom = marketFrom;
        this.marketTo = marketTo;
        this.coinTag = coinTag;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.buyVolume = buyVolume;
        this.sellVolume = sellVolume;
        this.lastUpdate = lastUpdate;
        buyFee = ExchangeBase.EXCHANGES
                .stream()
                .filter(s -> s.getExchangeModel().getExchangeName().equals(marketFrom))
                .map(s -> s.getExchangeModel().getExchangeFee(CoinList.valueOf(coinTag)))
                .findFirst()
                .orElse(0.0);
        withdrawalFee = ExchangeBase.EXCHANGES
                .stream()
                .filter(s -> s.getExchangeModel().getExchangeName().equals(marketFrom))
                .map(s -> s.getExchangeModel().getWirhdrawalFee(CoinList.valueOf(coinTag)))
                .findFirst()
                .orElse(0.0);
        sellFee = ExchangeBase.EXCHANGES
                .stream()
                .filter(s -> s.getExchangeModel().getExchangeName().equals(marketTo))
                .map(s -> s.getExchangeModel().getWirhdrawalFee(CoinList.valueOf(coinTag)))
                .findFirst()
                .orElse(0.0);
        try {
            double d = (sellPrice - buyPrice) / buyPrice * 100;
            this.earnPercentage = BigDecimal.valueOf(d)
                    .setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        }catch (NumberFormatException e){
            System.err.println("Z:"+marketFrom +" cena:"+buyPrice+" tag:"+coinTag +"do:"+marketTo+" cena:"+sellPrice);
        }
    }

    @Override
    public String toString() {
        return "Arbitraż: [" +
                "Kupić na = " + marketFrom +
                " Sprzedać na = " + marketTo +
                " Waluta = " + coinTag + " | " +
                " Cena kupna = " + String.format("%.8f", buyPrice) + " | " +
                " Cena sprzedaży = " + String.format("%.8f", sellPrice) + " | " +
                " zysk = " + earnPercentage + "%" + " ]";
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public double getBuyFee() {
        return buyFee;
    }

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    public double getSellFee() {
        return sellFee;
    }

    public String getMarketFrom() {
        return marketFrom;
    }

    public String getMarketTo() {
        return marketTo;
    }

    public String getCoinTag() {
        return coinTag;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public double getEarnPercentage() {
        return earnPercentage;
    }

    public double getBuyVolume() {
        return buyVolume;
    }

    public double getSellVolume() {
        return sellVolume;
    }
}

