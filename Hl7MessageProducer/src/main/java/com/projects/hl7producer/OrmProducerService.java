package com.projects.hl7producer;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.v22.message.ORM_O01;
import com.projects.hl7producer.generators.HospitalDataGenerator;
import com.projects.hl7producer.generators.structs.Exam;
import com.projects.hl7producer.producers.MessageProducer;
import com.projects.hl7producer.senders.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class OrmProducerService {
    private final static Logger logger = LoggerFactory.getLogger(OrmProducerService.class);

    private final MessageSender messageSender;
    private final MessageProducer<ORM_O01, Exam> messageProducer;
    private final HospitalDataGenerator hospitalDataGenerator;

    public OrmProducerService(@Autowired MessageSender messageSender,
                              @Autowired MessageProducer<ORM_O01, Exam> messageProducer,
                              @Autowired HospitalDataGenerator hospitalDataGenerator
    ) {
        this.messageSender = messageSender;
        this.messageProducer = messageProducer;
        this.hospitalDataGenerator = hospitalDataGenerator;
    }

    @Scheduled(fixedRate=5000)
    public void produce() throws HL7Exception, LLPException, IOException {
        logger.info("Producing new order message");
        var patient = hospitalDataGenerator.getNextPatient();
        var order = hospitalDataGenerator.getNextExam(patient);
        var message = messageProducer.produce(order, hospitalDataGenerator.getIdGenerator().getNextProcessingId());
        messageSender.send(message);
    }
}
