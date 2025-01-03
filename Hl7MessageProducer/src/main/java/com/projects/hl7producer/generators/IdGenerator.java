package com.projects.hl7producer.generators;

import com.projects.hl7producer.generators.structs.HL7Id;

public interface IdGenerator {
    String getNextProcessingId();
    HL7Id getNextPatientId();
    HL7Id getNextExamId();
}
