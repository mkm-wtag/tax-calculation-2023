package com.example.application.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

@BrowserCallable
@AnonymousAllowed
@Service
public class IncomeTaxCalculator {

    public Double calculateTotalTax(Long income) {
        double taxFreeIncome = income / 3.0;
        double taxableIncome = income - Math.min(450000, taxFreeIncome);
        int[] rates = {10, 15, 20, 25};
        int[] slabAmounts = {300000, 400000, 500000, 2000000};
        double totalTax = 0;
        taxableIncome = Math.max(0, taxableIncome - 375000);
        for (int i = 0; i < rates.length; i++) {
            totalTax += Math.min(taxableIncome, slabAmounts[i]) * rates[i] / 100;
            taxableIncome = Math.max(0, taxableIncome - slabAmounts[i]);
        }
        totalTax += taxableIncome * 25 / 100;
        return totalTax;
    }
}
