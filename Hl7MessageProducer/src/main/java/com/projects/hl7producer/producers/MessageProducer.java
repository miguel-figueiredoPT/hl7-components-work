package com.projects.hl7producer.producers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import java.io.IOException;

public interface MessageProducer<T extends Message, V extends Record> {
    T produce(V record, String processingId) throws HL7Exception, IOException;
}
