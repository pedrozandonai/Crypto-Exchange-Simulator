package pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi;

public class QuoteData {
    private double price;
    private long volume_24h;
    private double market_cap;
    private long circulating_supply;
    private long total_supply;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(long volume_24h) {
        this.volume_24h = volume_24h;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(double market_cap) {
        this.market_cap = market_cap;
    }

    public long getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(long circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public long getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(long total_supply) {
        this.total_supply = total_supply;
    }
}
