package com.projects.hl7producer.producers.fillers;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.message.ADT_A01;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;
import com.projects.hl7producer.generators.structs.Patient;
import com.projects.hl7producer.producers.fillers.segments.MshFiller;
import com.projects.hl7producer.producers.fillers.segments.PidFiller;
import com.projects.hl7producer.producers.fillers.segments.Pv1Filler;
import org.springframework.stereotype.Component;

@Component
public class DefaultAdtFiller implements MessageSegmentFiller<ADT_A01, Patient>, MshFiller, PidFiller, Pv1Filler {
    public void fill(ADT_A01 message, Patient record, HospitalDataGeneratorSettings context) throws DataTypeException {
        fillMSH(message.getMSH(), context);
        fillPid(message.getPID(), record);
        fillPv1(message.getPV1(), record);
    }
}
