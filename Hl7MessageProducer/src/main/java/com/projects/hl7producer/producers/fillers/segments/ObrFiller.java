package com.projects.hl7producer.producers.fillers.segments;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.OBR;
import com.projects.hl7producer.generators.structs.Exam;

public interface ObrFiller {
    default void fillObr(OBR obr, Exam exam) throws DataTypeException {
        obr.getObr3_FillerOrderNumber().getCm_filler1_UniqueFillerId().setValue(exam.requestId().id());
        obr.getObr3_FillerOrderNumber().getCm_filler2_FillerApplicationID().setValue(exam.requestId().assigningAuthorityNamespace());
    }
}
