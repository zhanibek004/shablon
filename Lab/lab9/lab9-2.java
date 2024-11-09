import java.util.Scanner;

/* Adapter */
public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Выберите валюту для оплаты:");
        System.out.println("1. USD");
        System.out.println("2. EUR");
        System.out.println("3. KZT");
        System.out.print("Введите ваш выбор (1-3): ");
        
        String input = scanner.nextLine();
        IPaymentProcessor paymentProcessor = null;

        try {
            switch (input) {
                case "1":
                    paymentProcessor = PaymentProcessorFactory.createProcessor("USD");
                    break;
                case "2":
                    paymentProcessor = PaymentProcessorFactory.createProcessor("EUR");
                    break;
                case "3":
                    paymentProcessor = PaymentProcessorFactory.createProcessor("KZT");
                    break;
                default:
                    System.out.println("Неверный выбор, перезапустите.");
                    return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        paymentProcessor.processPayment(100.0);
        paymentProcessor.refundPayment(50.0);
        
        scanner.close();
    }
}

interface IPaymentProcessor {
    void processPayment(double amount);
    void refundPayment(double amount);
}

class InternalPaymentProcessor implements IPaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Обработка платежа " + amount + " через внутреннюю систему.");
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Возврат платежа " + amount + " через внутреннюю систему.");
    }
}

class ExternalPaymentSystemA {
    public void makePayment(double amount) {
        System.out.println("Оплата " + amount + " через внешнюю платежную систему A.");
    }

    public void makeRefund(double amount) {
        System.out.println("Возврат " + amount + " через внешнюю платежную систему A.");
    }
}

class ExternalPaymentSystemB {
    public void sendPayment(double amount) {
        System.out.println("Отправка платежа " + amount + " через внешнюю платежную систему B.");
    }

    public void processRefund(double amount) {
        System.out.println("Обработка возврата " + amount + " через внешнюю платежную систему B.");
    }
}

class PaymentAdapterA implements IPaymentProcessor {
    private ExternalPaymentSystemA externalSystemA;

    public PaymentAdapterA(ExternalPaymentSystemA externalSystemA) {
        this.externalSystemA = externalSystemA;
    }

    @Override
    public void processPayment(double amount) {
        externalSystemA.makePayment(amount);
    }

    @Override
    public void refundPayment(double amount) {
        externalSystemA.makeRefund(amount);
    }
}

class PaymentAdapterB implements IPaymentProcessor {
    private ExternalPaymentSystemB externalSystemB;

    public PaymentAdapterB(ExternalPaymentSystemB externalSystemB) {
        this.externalSystemB = externalSystemB;
    }

    @Override
    public void processPayment(double amount) {
        externalSystemB.sendPayment(amount);
    }

    @Override
    public void refundPayment(double amount) {
        externalSystemB.processRefund(amount);
    }
}

/* Factory */
class PaymentProcessorFactory {
    public static IPaymentProcessor createProcessor(String currency) {
        switch (currency) {
            case "USD":
                return new InternalPaymentProcessor();
            case "EUR":
                return new PaymentAdapterA(new ExternalPaymentSystemA());
            case "KZT":
                return new PaymentAdapterB(new ExternalPaymentSystemB());
            default:
                throw new IllegalArgumentException("Валюта не поддерживается.");
        }
    }
}
