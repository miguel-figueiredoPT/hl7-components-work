package com.projects.hl7producer.producers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v22.message.ADT_A01;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;
import com.projects.hl7producer.generators.structs.Patient;
import com.projects.hl7producer.producers.fillers.MessageSegmentFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdtProducer extends AbstractProducer<ADT_A01, Patient> {

    public AdtProducer(@Autowired HospitalDataGeneratorSettings hospitalDataGeneratorSettings,
                       @Autowired MessageSegmentFiller<ADT_A01, Patient> messageSegmentFiller) {
        super(hospitalDataGeneratorSettings, messageSegmentFiller);
    }

    @Override
    public ADT_A01 produce(Patient record, String processingId) throws HL7Exception, IOException {
        ADT_A01 message = new ADT_A01();
        message.initQuickstart("ADT", "A08", processingId);

        messageSegmentFiller.fill(message, record, hospitalDataGeneratorSettings);

        return message;
    }


}
