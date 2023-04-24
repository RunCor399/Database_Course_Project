package it.unibo.bd.model;

import java.util.Date;
import java.util.Optional;

public class Tax {
    private String airlineCode;
    private Date billingDate;
    private Optional<Date> paymentDate;
    private Optional<Integer> amount;
    
    public Tax(String airlineCode, Date billingDate) {
        this.airlineCode = airlineCode;
        this.billingDate = billingDate;
    }
    
    
    public String getAirlineCode() {
        return airlineCode;
    }
    
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
    
    public Date getBillingDate() {
        return billingDate;
    }
    
    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }
    
    public Optional<Date> getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Optional<Date> paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public Optional<Integer> getAmount() {
        return this.amount;
    }
    
    public void setAmount(int amount) {
        Optional<Integer> newAmount = Optional.of(amount);
        this.amount = newAmount;
    }
    
}
