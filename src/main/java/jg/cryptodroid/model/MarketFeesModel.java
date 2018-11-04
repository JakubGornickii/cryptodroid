package jg.cryptodroid.model;

import jg.cryptodroid.enums.CoinList;

public class MarketFeesModel {
    private CoinList token;
    private double exchangeFee;
    private double withdrawalFee;

    public MarketFeesModel(CoinList token, double exchangeFee, double withdrawalFee) {
        this.token = token;
        this.exchangeFee = exchangeFee;
        this.withdrawalFee = withdrawalFee;
    }

    public CoinList getToken() {
        return token;
    }

    public void setToken(CoinList token) {
        this.token = token;
    }

    public double getExchangeFee() {
        return exchangeFee;
    }

    public void setExchangeFee(double exchangeFee) {
        this.exchangeFee = exchangeFee;
    }

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(double withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }
}
