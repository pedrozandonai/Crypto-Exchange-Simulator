package pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi;

public class Quote {
    private String timestamp;
    private QuoteData quote;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public QuoteData getQuote() {
        return quote;
    }

    public void setQuote(QuoteData quote) {
        this.quote = quote;
    }
}
