package cm.belrose.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private Sender sender;
    private Receiver receiver;
    private double amount;
    private String currency;
    private String date;
    private String reference;

    @Data
    public static class Sender {
        private String bank;
        private String account;
    }

    @Data
    public static class Receiver {
        private String bank;
        private String account;
    }
}