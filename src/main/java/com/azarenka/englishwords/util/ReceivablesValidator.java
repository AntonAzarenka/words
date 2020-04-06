package com.azarenka.englishwords.util;

import com.azarenka.englishwords.domain.Receivables;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ReceivablesValidator {

    @Value("${spring.main.count-contributors}")
    private int contributorCount;

    public boolean validate(Set<Receivables> receivablesList) {
        if (receivablesList.size() > 9) {
            System.out.println("Имеются дубликаты записей должников");
        }
        return true;
    }
}
