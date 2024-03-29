package com.example.drinkstore.dto;

public class ChargeRequest {

    private String description;
    private int amount;
    private String currency;
    private String StripeToken;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStripeToken() {
        return StripeToken;
    }

    public void setStripeToken(String stripeToken) {
        StripeToken = stripeToken;
    }
}
