package com.projects.producers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import java.io.IOException;

public interface MessageProducer<T extends Message, V extends Record> {
    T produce(V record) throws HL7Exception, IOException;
}
