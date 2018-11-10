package jg.cryptodroid.modelDTO;

import jg.cryptodroid.model.MarketFeesModel;

import java.util.List;

public class ExchangeDetailsDTO {
    private String name;
    private List<ExchangeCoinModelDTO> coins;
    private List<MarketFeesModel> fees;
    private boolean active;
    private long lastUpdate;

    public ExchangeDetailsDTO(String name, List<ExchangeCoinModelDTO> coins, boolean active, long lastUpdate, List<MarketFeesModel> fees) {
        this.name = name;
        this.coins = coins;
        this.active = active;
        this.lastUpdate = lastUpdate;
        this.fees = fees;
    }

    public String getName() {
        return name;
    }

    public List<ExchangeCoinModelDTO> getCoins() {
        return coins;
    }

    public boolean isActive() {
        return active;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public List<MarketFeesModel> getFees() {
        return fees;
    }
}
