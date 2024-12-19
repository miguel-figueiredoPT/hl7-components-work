package com.projects.producers.fillers.defaults;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.PV1;
import com.projects.producers.data.Patient;

public interface Pv1Filler {

    default void fillPv1(PV1 pv1, Patient patient) throws DataTypeException {
        pv1.getPv12_PatientClass().setValue("E");
    }
}
