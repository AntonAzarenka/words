package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Contributor;
import com.azarenka.englishwords.domain.Receivables;
import com.azarenka.englishwords.domain.Report;
import com.azarenka.englishwords.util.ReceivablesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class BookerService {
    @Autowired
    private ReceivablesValidator receivablesValidator;
    @Autowired
    private ReceivablesLoader loader;
    private Set<Receivables> receivablesList;

    public Set<Receivables> getReceivables() {
        receivablesList = loader.load();
        receivablesValidator.validate(receivablesList);
        return receivablesList;
    }

    public void setReceivable(Contributor contributor, String word, int money) {
        getReceivables();
        if (receivablesList.isEmpty()) {
            Receivables receivables = createReceivable(contributor, word, money);
            receivablesList.add(receivables);
        } else {
            Receivables receivables = findReceivables(contributor);
            if (null != receivables) {
                setAmountsForContributor(receivables, word, money);
                receivables.setContributor(contributor);
            } else {
                receivables = new Receivables();
                setAmountsForContributor(receivables, word, money);
                receivables.setContributor(contributor);
                receivablesList.add(receivables);
            }
        }
        loader.upload(receivablesList);
    }


    public List<Report> getReport() {
        getReceivables();
        List<Report> reports = new ArrayList<>();
        if (receivablesList != null) {
            receivablesList.forEach(element -> {
                Report report = new Report();
                report.setContributor(element.getContributor().toString());
                report.setOutcome(element.getTotalAmount().toString());
                report.setWords(calculateCountWords(element));
                reports.add(report);
            });
        }
        return reports;
    }

    private Receivables findReceivables(Contributor contributor) {
        for (Receivables r : receivablesList) {
            if (r.getContributor().equals(contributor)) {
                return r;
            }
        }
        return null;
    }

    private Receivables createReceivable(Contributor contributor, String word, int money) {
        Receivables receivables = new Receivables();
        receivables.setContributor(contributor);
        Map<Map<LocalDateTime, BigDecimal>, String> amounts = new HashMap<>();
        Map<LocalDateTime, BigDecimal> innerMap = new HashMap<>();
        innerMap.put(LocalDateTime.now(), new BigDecimal(money));
        amounts.put(innerMap, word);
        receivables.setAmounts(amounts);
        BigDecimal totalAmount = new BigDecimal(money).divide(new BigDecimal("100"));
        receivables.setTotalAmount(totalAmount);
        return receivables;
    }

    private String calculateCountWords(Receivables receivable) {
        return String.valueOf(receivable.getAmounts().size());
    }

    private void setAmountsForContributor(Receivables receivable, String word, int money) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        Map<Map<LocalDateTime, BigDecimal>, String> amounts = receivable.getAmounts();
        Map<LocalDateTime, BigDecimal> tempMap = new HashMap<>();
        tempMap.put(LocalDateTime.now(), new BigDecimal(money));
        amounts.put(tempMap, word);
        for (Map.Entry<Map<LocalDateTime, BigDecimal>, String> pair : amounts.entrySet()) {
            Map<LocalDateTime, BigDecimal> innerMap = pair.getKey();
            totalAmount = totalAmount.add(getCurrentValue(innerMap));
        }
        receivable.setTotalAmount(totalAmount);
    }

    private BigDecimal getCurrentValue(Map<LocalDateTime, BigDecimal> map) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Map.Entry<LocalDateTime, BigDecimal> innerPair : map.entrySet()) {
            totalAmount = totalAmount.add(innerPair.getValue());
        }
        totalAmount = totalAmount.divide(new BigDecimal("100"));
        return totalAmount;
    }

    public String getOutcome() {
        getReceivables();
        BigDecimal outcome = BigDecimal.ZERO;
        if (receivablesList.size() > 0) {
            for (Receivables element : receivablesList) {
                Map<Map<LocalDateTime, BigDecimal>, String> amounts = element.getAmounts();
                for (Map.Entry<Map<LocalDateTime, BigDecimal>, String> pair : amounts.entrySet()) {
                    Map<LocalDateTime, BigDecimal> innerMap = pair.getKey();
                    outcome = outcome.add(getCurrentValue(innerMap));
                }
            }
        }
        return String.valueOf(outcome);
    }
}
