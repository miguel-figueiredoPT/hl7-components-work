package com.projects.producers.fillers;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.*;
import com.projects.producers.data.Exam;
import com.projects.producers.fillers.defaults.MshFiller;
import com.projects.producers.fillers.defaults.PidFiller;
import com.projects.producers.fillers.defaults.Pv1Filler;

public interface OrmO01SegmentFiller extends MshFiller, PidFiller, Pv1Filler {
    default void fillOrc(ORC orc, Exam exam) throws DataTypeException {
        orc.getOrc2_PlacerOrderNumber().getUniquePlacerId().setValue(exam.requestId().id());
        orc.getOrc2_PlacerOrderNumber().getPlacerApplication().setValue(exam.requestId().assigningAuthorityNamespace());
        orc.getOrc5_OrderStatus().setValue(exam.status());
        orc.getOrc9_DateTimeOfTransaction().getTs1_TimeOfAnEvent().setValue(exam.examDate());
        orc.getOrc15_OrderEffectiveDateTime().getTs1_TimeOfAnEvent().setValue(exam.examDate());

        orc.getOrc17_EnteringOrganization().getCe2_Text().setValue(exam.enteringOrganization());
    }

    default void fillObr(OBR obr, Exam exam) throws DataTypeException {
        obr.getObr3_FillerOrderNumber().getCm_filler1_UniqueFillerId().setValue(exam.requestId().id());
        obr.getObr3_FillerOrderNumber().getCm_filler2_FillerApplicationID().setValue(exam.requestId().assigningAuthorityNamespace());
    }
}
