package com.projects.hl7producer.producers.fillers.segments;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.ORC;
import com.projects.hl7producer.generators.structs.Exam;

public interface OrcFiller {
    default void fillOrc(ORC orc, Exam exam) throws DataTypeException {
        orc.getOrc2_PlacerOrderNumber().getUniquePlacerId().setValue(exam.requestId().id());
        orc.getOrc2_PlacerOrderNumber().getPlacerApplication().setValue(exam.requestId().assigningAuthorityNamespace());
        orc.getOrc5_OrderStatus().setValue(exam.status());
        orc.getOrc9_DateTimeOfTransaction().getTs1_TimeOfAnEvent().setValue(exam.examDate());
        orc.getOrc15_OrderEffectiveDateTime().getTs1_TimeOfAnEvent().setValue(exam.examDate());

        orc.getOrc17_EnteringOrganization().getCe2_Text().setValue(exam.enteringOrganization());
    }
}
