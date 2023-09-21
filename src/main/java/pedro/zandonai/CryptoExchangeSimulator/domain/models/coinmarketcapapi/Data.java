package pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private int id;
    private String name;
    private String symbol;
    private int is_active;
    private int is_fiat;
    private List<Quote> quotes = new ArrayList<>();

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

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getIs_fiat() {
        return is_fiat;
    }

    public void setIs_fiat(int is_fiat) {
        this.is_fiat = is_fiat;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
