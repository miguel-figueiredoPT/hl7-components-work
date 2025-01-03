package com.projects.hl7producer.generators.structs;

public record HL7Id(String id, String assigningAuthorityNamespace) {

    public String toHl7Format() {
        return id + "^^" + assigningAuthorityNamespace;
    }
}
