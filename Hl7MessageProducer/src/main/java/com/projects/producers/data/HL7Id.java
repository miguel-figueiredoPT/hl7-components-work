package com.projects.producers.data;

public record HL7Id(String id, String assigningAuthorityNamespace) {

    public String toHl7Format() {
        return id + "^^" + assigningAuthorityNamespace;
    }
}
