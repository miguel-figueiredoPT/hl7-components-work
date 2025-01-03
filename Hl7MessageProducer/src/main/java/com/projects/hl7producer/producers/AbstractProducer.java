package com.projects.hl7producer.producers;

import ca.uhn.hl7v2.model.Message;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;
import com.projects.hl7producer.producers.fillers.MessageSegmentFiller;

public abstract class AbstractProducer<T extends Message, V extends Record>  implements MessageProducer<T, V> {
    protected final HospitalDataGeneratorSettings hospitalDataGeneratorSettings;
    protected final MessageSegmentFiller<T,V> messageSegmentFiller;

    public AbstractProducer(HospitalDataGeneratorSettings hospitalDataGeneratorSettings, MessageSegmentFiller<T,V> messageSegmentFiller) {
        this.hospitalDataGeneratorSettings = hospitalDataGeneratorSettings;
        this.messageSegmentFiller = messageSegmentFiller;
    }
}
