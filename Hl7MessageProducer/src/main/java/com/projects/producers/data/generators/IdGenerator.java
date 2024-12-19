package com.projects.producers.data.generators;

import com.projects.producers.data.HL7Id;

public interface IdGenerator {
    String getNextProcessingId();
    HL7Id getNextPatientId();
    HL7Id getNextExamId();
}
