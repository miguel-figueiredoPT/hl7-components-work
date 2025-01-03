package com.projects.hl7producer.producers.fillers;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;

public interface MessageSegmentFiller<T extends Message, V extends Record> {
    void fill(T message, V record, HospitalDataGeneratorSettings context) throws DataTypeException;
}
