package com.azarenka.englishwords.services;

import com.azarenka.englishwords.domain.Contributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContributorParser {

    @Autowired
    private ContributorReader reader;

    public List<Contributor> getContributors() {
        List<Contributor> contributors = new ArrayList<>();
        List<String> contributorList = reader.getContributors();
        contributorList.forEach(contr -> contributors.add(createContributor(contr)));
        return contributors;
    }

    private Contributor createContributor(String str) {
        Contributor contributor = new Contributor();
        String[] strings = str.split(",");
        if (strings.length == 2) {
            contributor.setFirstName(strings[0]);
            contributor.setLastName(strings[1]);
        }
        return contributor;
    }
}
