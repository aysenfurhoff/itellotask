/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.task.registration.internal;

/**
 *
 * @author Furhoff
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import se.itello.task.payments.PaymentReceiver;
import se.itello.task.payments.PaymentReceiverDummy;


//Superklass till handlers av inbetalningsfiler
public abstract class PaymentFileHandler extends FileHandler{
    
    private class Payment {
        private final BigDecimal amount;
        private final String reference;
        Payment(BigDecimal amount, String reference) {
            this.amount = amount;
            this.reference = reference;
        }
    }
    private final List<Payment> payments;
    
    private String accountNumber;
    private Date paymentDate;
    private String currency;
    
    private final PaymentReceiver paymentReceiver;
    
    
    protected PaymentFileHandler() {
        this.paymentReceiver = new PaymentReceiverDummy();
        payments = new ArrayList<>();
    }
    protected void setPaymentInfo(String accountNumber, Date paymentDate, String currency){
        this.accountNumber = accountNumber;
        this.paymentDate = paymentDate;
        this.currency = currency;
    }
    protected void addPayment(BigDecimal amount, String reference) {
        payments.add( new Payment(amount, reference) );
    }
    @Override
    public void registerData() {
        paymentReceiver.startPaymentBundle(accountNumber, paymentDate, currency);
        for(Payment p : payments) {
            paymentReceiver.payment(p.amount, p.reference);
        }
        paymentReceiver.endPaymentBundle();
    }
    
    
}
