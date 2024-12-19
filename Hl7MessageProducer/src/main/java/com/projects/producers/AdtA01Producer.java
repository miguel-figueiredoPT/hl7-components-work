package com.projects.producers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v22.message.ADT_A01;
import com.projects.producers.data.Patient;
import com.projects.producers.data.generators.context.HospitalDataContext;
import com.projects.producers.data.generators.IdGenerator;
import com.projects.producers.fillers.AdtA01SegmentFiller;

import java.io.IOException;

public class AdtA01Producer implements MessageProducer<ADT_A01, Patient>, AdtA01SegmentFiller {
    private final HospitalDataContext hospitalDataContext;
    private final IdGenerator idGenerator;

    public AdtA01Producer(HospitalDataContext hospitalDataContext, IdGenerator idGenerator) {
        this.hospitalDataContext = hospitalDataContext;
        this.idGenerator = idGenerator;
    }

    @Override
    public ADT_A01 produce(Patient record) throws HL7Exception, IOException {
        ADT_A01 message = new ADT_A01();
        message.initQuickstart("ADT", "A08", idGenerator.getNextProcessingId());

        fillMSH(message.getMSH(), hospitalDataContext);
        fillPid(message.getPID(), record);
        fillPv1(message.getPV1(), record);

        return message;
    }
}
