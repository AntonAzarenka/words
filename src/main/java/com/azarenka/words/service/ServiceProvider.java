package com.azarenka.words.service;

import com.azarenka.words.domain.Word;
import com.azarenka.words.file.ResourceProvider;
import com.azarenka.words.service.options.OptionService;
import com.azarenka.words.service.participants.ParticipantParser;
import com.azarenka.words.service.participants.ParticipantService;
import com.azarenka.words.service.tableview.ITableManager;
import com.azarenka.words.service.trigger.RefreshService;
import com.azarenka.words.service.word.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Autowired
    private IWordService wordService;
    @Autowired
    private ITableManager<Word> wordsTableManager;
    @Autowired
    private ParticipantParser participantParser;
    @Autowired
    private ResourceProvider resourceProvider;
    @Autowired
    private OptionService optionService;
    @Autowired
    private RefreshService refreshService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private StatisticService statisticService;

    public ParticipantParser getParticipantParser() {
        return participantParser;
    }

    public IWordService getWordService() {
        return wordService;
    }

    public ITableManager<Word> getWordsTableManager() {
        return wordsTableManager;
    }

    public ResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    public OptionService getOptionService() {
        return optionService;
    }

    public RefreshService getRefreshService() {
        return refreshService;
    }

    public ParticipantService getParticipantService() {
        return participantService;
    }

    public StatisticService getStatisticService() {
        return statisticService;
    }
}
