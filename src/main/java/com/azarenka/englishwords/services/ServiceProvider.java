package com.azarenka.englishwords.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Autowired
    private AnswerMemory answerMemory;
    @Autowired
    private WordParser wordParser;
    @Autowired
    private Randomizer randomizer;
    @Autowired
    private WordReader reader;
    @Autowired
    private ContributorParser contributorParser;
    @Autowired
    private BookerService bookerService;
    @Autowired
    private Writer writer;
    @Autowired
    private Searcher searcher;

    public Searcher getSearcher() {
        return searcher;
    }

    public Writer getWriter() {
        return writer;
    }

    public BookerService getBookerService() {
        return bookerService;
    }

    public ContributorParser getContributorParser() {
        return contributorParser;
    }

    public AnswerMemory getAnswerMemory() {
        return answerMemory;
    }

    public WordParser getWordParser() {
        return wordParser;
    }

    public Randomizer getRandomizer() {
        return randomizer;
    }

    public WordReader getReader() {
        return reader;
    }
}
