import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select payment method:");
        System.out.println("1 - PayPal");
        System.out.println("2 - Credit Card");
        System.out.println("3 - Crypto");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 3) {
            System.out.println("Choice only from given options.");
            scanner.close();
            return;
        }

        switch (choice) {
            case 1:
                context.setPaymentStrategy(new PayPalPay());
                System.out.println("Paid with PayPal.");
                break;
            case 2:
                context.setPaymentStrategy(new CreditCardPay());
                System.out.println("Paid with Credit Card.");
                break;
            case 3:
                context.setPaymentStrategy(new CryptoPay());
                System.out.println("Paid with Crypto.");
                break;
        }

        context.paymentExec();
        scanner.close();
    }
}

interface PaymentStrategy {
    void pay();
}

class PayPalPay implements PaymentStrategy {
    public void pay() {
        System.out.println("Processing payment through PayPal...");
    }
}

class CreditCardPay implements PaymentStrategy {
    public void pay() {
        System.out.println("Processing payment through Credit Card...");
    }
}

class CryptoPay implements PaymentStrategy {
    public void pay() {
        System.out.println("Processing payment through Crypto...");
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strat) {
        this.paymentStrategy = strat;
    }

    public void paymentExec() {
        if (paymentStrategy == null) {
            throw new IllegalStateException("There is no Payment strategy set.");
        }
        paymentStrategy.pay();
    }
}
