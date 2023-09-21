package pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi;

public class CryptoInfo {
    private int id;
    private String name;
    private String symbol;
    private int num_market_pairs;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNum_market_pairs() {
        return num_market_pairs;
    }

    public void setNum_market_pairs(int num_market_pairs) {
        this.num_market_pairs = num_market_pairs;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
