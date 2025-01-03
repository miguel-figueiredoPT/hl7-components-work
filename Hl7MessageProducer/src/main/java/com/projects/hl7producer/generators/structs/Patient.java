package com.projects.hl7producer.generators.structs;

public record Patient(HL7Id patientId,
                      String firstName,
                      String middleName,
                      String lastName,
                      char sex,
                      Address patientAddress) {
}
