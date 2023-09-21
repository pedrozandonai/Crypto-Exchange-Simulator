package pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi;

import pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi.Data;
public class CryptoData {
    private Data data;
    private Status status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
