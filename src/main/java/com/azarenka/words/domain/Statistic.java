package com.azarenka.words.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Statistic implements Serializable {

    private Set<ParticipantInformation> contributorInformation;

    /**
     * Default constructor.
     */
    public Statistic() {
    }

    /**
     * Constructor.
     *
     * @param contributorInformation information about participant
     */
    public Statistic(Set<ParticipantInformation> contributorInformation) {
        this.contributorInformation = contributorInformation;
    }

    public ParticipantInformation getInformation(Participant participant) {
        return contributorInformation.stream()
                .filter(participantInformation -> participantInformation.getParticipant().equals(participant))
                .findFirst()
                .orElse(null);
    }

    public Set<ParticipantInformation> getContributorInformation() {
        return contributorInformation;
    }

    public void setContributorInformation(Set<ParticipantInformation> contributorInformation) {
        this.contributorInformation = contributorInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(contributorInformation, statistic.contributorInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contributorInformation);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "contributorInformation=" + contributorInformation +
                '}';
    }
}
