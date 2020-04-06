package com.azarenka.englishwords.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContributorReader extends Reader {

    @Value(value = "${spring.main.contributors}")
    private Resource filename;

    public List<String> getContributors() {
        List<String> stringList = readFile(filename);
        return stringList;
    }
}
