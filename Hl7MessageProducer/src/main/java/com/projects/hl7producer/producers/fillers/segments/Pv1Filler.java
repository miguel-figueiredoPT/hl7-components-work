package com.projects.hl7producer.producers.fillers.segments;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.PV1;
import com.projects.hl7producer.generators.structs.Patient;

public interface Pv1Filler {

    default void fillPv1(PV1 pv1, Patient patient) throws DataTypeException {
        pv1.getPv12_PatientClass().setValue("E");
    }
}
