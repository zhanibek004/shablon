import java.util.*;

public class Program {
    public static void main(String[] args) {
        IPaymentProcessor paypalProcessor = new PayPalPaymentProcessor();
        paypalProcessor.processPayment(100.0);
        paypalProcessor.refundPayment(50.0);

        StripePaymentService stripeService = new StripePaymentService();
        IPaymentProcessor stripeProcessor = new StripePaymentAdapter(stripeService);
        stripeProcessor.processPayment(200.0);
        stripeProcessor.refundPayment(100.0);

        ExternalPaymentService externalService = new ExternalPaymentService();
        IPaymentProcessor externalProcessor = new ExternalPaymentAdapter(externalService);
        externalProcessor.processPayment(300.0);
        externalProcessor.refundPayment(150.0);
    }
}

interface IPaymentProcessor {
    void processPayment(double amount);
    void refundPayment(double amount);
}

class PayPalPaymentProcessor implements IPaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment " + amount + " via PayPal.\n");
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Refunding payment " + amount + " via PayPal.\n");
    }
}

class StripePaymentService {
    public void makeTransaction(double totalAmount) {
        System.out.println("Making transaction " + totalAmount + " via Stripe.\n");
    }

    public void makeRefund(double totalAmount) {
        System.out.println("Refunding transaction " + totalAmount + " via Stripe.\n");
    }
}

class StripePaymentAdapter implements IPaymentProcessor {
    private final StripePaymentService stripePaymentService;

    public StripePaymentAdapter(StripePaymentService stripePaymentService) {
        this.stripePaymentService = stripePaymentService;
    }

    @Override
    public void processPayment(double amount) {
        stripePaymentService.makeTransaction(amount);
    }

    @Override
    public void refundPayment(double amount) {
        stripePaymentService.makeRefund(amount);
    }
}

class ExternalPaymentService {
    public void executePayment(double amount) {
        System.out.println("Executing payment " + amount + " via External Payment Service.\n");
    }

    public void executeRefund(double amount) {
        System.out.println("Executing refund " + amount + " via External Payment Service.\n");
    }
}

class ExternalPaymentAdapter implements IPaymentProcessor {
    private final ExternalPaymentService externalPaymentService;

    public ExternalPaymentAdapter(ExternalPaymentService externalPaymentService) {
        this.externalPaymentService = externalPaymentService;
    }

    @Override
    public void processPayment(double amount) {
        externalPaymentService.executePayment(amount);
    }

    @Override
    public void refundPayment(double amount) {
        externalPaymentService.executeRefund(amount);
    }
}

