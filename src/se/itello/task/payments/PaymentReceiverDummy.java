/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.task.payments;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Furhoff
 */

public class PaymentReceiverDummy implements PaymentReceiver{
    String name = this.getClass().getSimpleName();
    @Override
    //startPaymentBundle()
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) { 
        System.out.println(
                name+" accountNumber: " + accountNumber + 
                " paymentDate: " + paymentDate + 
                " currency: " + currency
        );
    }

    @Override
    //payment() 
    public void payment(BigDecimal amount, String reference) {
        System.out.println(
                name+" amount: " + amount + 
                " reference: " + reference 
        );
    }

    @Override
    public void endPaymentBundle() {
        System.out.println(name+".endPaymentBundle()" );
    }
    
}
