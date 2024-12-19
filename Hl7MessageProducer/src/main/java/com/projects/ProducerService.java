package com.projects;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import com.projects.producers.AdtA01Producer;
import com.projects.producers.OrmO01Producer;
import com.projects.producers.data.generators.HospitalDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ProducerService {
    Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private final MessageSender messageSender;
    private final AdtA01Producer adtA01Producer;
    private final OrmO01Producer ormO01Producer;
    private final HospitalDataGenerator hospitalDataGenerator;

    public ProducerService(@Autowired MessageSender messageSender,
                           @Autowired AdtA01Producer adtA01Producer,
                           @Autowired OrmO01Producer ormO01Producer,
                           @Autowired HospitalDataGenerator hospitalDataGenerator) {
        this.messageSender = messageSender;
        this.adtA01Producer = adtA01Producer;
        this.ormO01Producer = ormO01Producer;
        this.hospitalDataGenerator = hospitalDataGenerator;
    }

    @Scheduled(fixedRate=5000)
    public void produce() throws HL7Exception, LLPException, IOException {
        logger.info("Producing patient update message");

        var patient = hospitalDataGenerator.getNextPatient();
        Message message = adtA01Producer.produce(patient);
        messageSender.send(message);

        logger.info("Producing new order message");
        var order = hospitalDataGenerator.getNextExam(patient);
        message = ormO01Producer.produce(order);
        messageSender.send(message);
    }
}
