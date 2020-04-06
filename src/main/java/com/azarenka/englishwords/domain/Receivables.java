package com.azarenka.englishwords.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Receivables implements Serializable {

    private Contributor contributor;
    private BigDecimal totalAmount;
    private Map<Map<LocalDate, BigDecimal>, String> amounts = new HashMap<>();

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Map<Map<LocalDate, BigDecimal>, String> getAmounts() {
        return amounts;
    }

    public void setAmounts(Map<Map<LocalDate, BigDecimal>, String> amounts) {
        this.amounts = amounts;
    }

    @Override
    public String toString() {
        return "Receivables{" +
                "contributor=" + contributor +
                ", TotalAmount=" + totalAmount +
                ", amounts=" + amounts +
                '}';
    }
}
