package jg.cryptodroid.modelDTO;

public class ExchangeStatusDTO {
    private boolean active;
    private long lastUpdate;


    public ExchangeStatusDTO(boolean active, long lastUpdate) {
        this.active = active;
        this.lastUpdate = lastUpdate;
    }

    public boolean isActive() {
        return active;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }
}
