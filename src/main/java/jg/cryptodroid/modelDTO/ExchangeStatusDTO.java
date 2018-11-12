package jg.cryptodroid.modelDTO;

public class ExchangeStatusDTO {
    private String name;
    private boolean active;
    private long lastUpdate;


    public ExchangeStatusDTO(String name,boolean active, long lastUpdate) {
        this.name = name;
        this.active = active;
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }
}
