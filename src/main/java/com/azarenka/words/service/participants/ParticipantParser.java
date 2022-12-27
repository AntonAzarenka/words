package com.azarenka.words.service.participants;

import com.azarenka.words.domain.Participant;
import com.azarenka.words.service.participants.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Represents of participant parser class.
 * <p>
 * Copyright (C) 2022 antazarenko@gmail.com
 * <p>
 * Date: 12/26/2022
 *
 * @author Anton Azarenka
 */
@Component
public class ParticipantParser {

    @Autowired
    private ParticipantService participantService;
    private List<Participant> contributors;

    public Participant getNextContributor() {
        Participant contributor = null;
        if(contributors == null){
            contributors = getContributors();
        }

        if (!CollectionUtils.isEmpty(contributors)) {
            int index = (int) (Math.random() * contributors.size());
            contributor = contributors.get(index);
            contributors.remove(contributor);
        }
        return contributor;
    }

    public void reset(){
        contributors = null;
    }

    public List<Participant> getContributors() {
       return participantService.getParticipants();
    }
}
