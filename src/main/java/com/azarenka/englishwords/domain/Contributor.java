package com.azarenka.englishwords.domain;

import java.io.Serializable;

public class Contributor implements Serializable {

    private String firstName;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Contributor that = (Contributor) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(firstName)
                .append(lastName)
                .toHashCode();
    }
}
