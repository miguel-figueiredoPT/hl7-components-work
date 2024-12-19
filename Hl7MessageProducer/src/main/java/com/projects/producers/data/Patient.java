package com.projects.producers.data;

public record Patient(HL7Id patientId,
                      String firstName,
                      String middleName,
                      String lastName,
                      char sex,
                      Address patientAddress) {
}
