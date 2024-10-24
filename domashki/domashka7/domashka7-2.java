import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CurrencyExchange currencyExchange = new CurrencyExchange();
        ReportingObserver reportObserver = new ReportingObserver("KZT", 1.7, 0.2);
        NotificationObserver notifObserver = new NotificationObserver("USD", 2.6);

        currencyExchange.attach(reportObserver);
        System.out.println(reportObserver);

        currencyExchange.attach(notifObserver);
        System.out.println(notifObserver);

        System.out.println("Updating exchange rates...");
        currencyExchange.updateExchangeRate("USD", 2.3);
        currencyExchange.updateExchangeRate("KZT", 2.0);

        System.out.println("Removing notification observer...");
        currencyExchange.detach(notifObserver);

        currencyExchange.updateExchangeRate("USD", 2.7);
        currencyExchange.updateExchangeRate("KZT", 1.4);

        System.out.println("Reattaching notification observer...");
        currencyExchange.attach(notifObserver);

        currencyExchange.updateExchangeRate("USD", 2.9);
        currencyExchange.updateExchangeRate("KZT", 0.8);
    }
}

interface IObserver {
    void update(String currency, double rate);
}

interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notify(String currency, double rate);
}

class CurrencyExchange implements ISubject {
    private Map<String, Double> exchangeRates = new HashMap<>();
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String currency, double rate) {
        for (IObserver observer : observers) {
            observer.update(currency, rate);
        }
    }

    public void updateExchangeRate(String currency, double rate) {
        exchangeRates.put(currency, rate);
        notify(currency, rate);
    }
}

class ReportingObserver implements IObserver {
    private String currency;
    private double highPoint;
    private double lowPoint;

    public ReportingObserver(String currency, double highPoint, double lowPoint) {
        this.currency = currency;
        this.highPoint = highPoint;
        this.lowPoint = lowPoint;
    }

    @Override
    public void update(String currency, double rate) {
        if (this.currency.equals(currency)) {
            if (rate >= highPoint) {
                System.out.println(this.currency + " exchange rate is at a high point: " + rate);
            } else if (rate <= lowPoint) {
                System.out.println(this.currency + " exchange rate is at a low point: " + rate);
            }
        }
    }

    @Override
    public String toString() {
        return "ReportingObserver: currency=" + currency + ", highPoint=" + highPoint + ", lowPoint=" + lowPoint;
    }
}

class NotificationObserver implements IObserver {
    private String currency;
    private double changeLimit;

    public NotificationObserver(String currency, double changeLimit) {
        this.currency = currency;
        this.changeLimit = changeLimit;
    }

    @Override
    public void update(String currency, double rate) {
        if (this.currency.equals(currency)) {
            if (rate >= changeLimit) {
                System.out.println("Notification: " + currency + " exchange rate has increased to " + rate);
            } else {
                System.out.println("Notification: " + currency + " exchange rate has decreased to " + rate);
            }
        }
    }

    @Override
    public String toString() {
        return "NotificationObserver: currency=" + currency + ", significantly changed=" + changeLimit;
    }
}
