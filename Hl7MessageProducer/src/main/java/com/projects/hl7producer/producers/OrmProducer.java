package com.projects.hl7producer.producers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v22.message.ORM_O01;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;
import com.projects.hl7producer.generators.structs.Exam;
import com.projects.hl7producer.producers.fillers.MessageSegmentFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrmProducer extends AbstractProducer<ORM_O01, Exam> {
    public OrmProducer(@Autowired HospitalDataGeneratorSettings hospitalDataGeneratorSettings,
                       @Autowired MessageSegmentFiller<ORM_O01, Exam> messageSegmentFiller) {
        super(hospitalDataGeneratorSettings, messageSegmentFiller);
    }
    
    @Override
    public ORM_O01 produce(Exam record, String processingId) throws HL7Exception, IOException {
        ORM_O01 message = new ORM_O01();
        message.initQuickstart("ORM", "O01", processingId);

        messageSegmentFiller.fill(message, record, hospitalDataGeneratorSettings);

        return message;
    }
}
