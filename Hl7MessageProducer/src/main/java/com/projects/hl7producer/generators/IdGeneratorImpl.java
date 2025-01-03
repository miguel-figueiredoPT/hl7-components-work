package com.projects.hl7producer.generators;

import com.projects.hl7producer.generators.structs.HL7Id;
import com.projects.hl7producer.settings.IdGeneratorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IdGeneratorImpl implements IdGenerator{
    private final IdGeneratorSettings context;
    private final AtomicInteger processingId = new AtomicInteger(1);
    private final AtomicInteger patientId = new AtomicInteger(1);
    private final AtomicInteger requestId = new AtomicInteger(1);

    public IdGeneratorImpl(@Autowired IdGeneratorSettings context) {
        this.context = context;
    }

    public String getNextProcessingId() {
        return context.processingIdPrefix() + processingId.getAndIncrement();
    }

    public HL7Id getNextPatientId() {
        return new HL7Id(context.patientPrefix() + patientId.getAndIncrement(),
                context.patientIssuerName());
    }

    public HL7Id getNextExamId() {
        return new HL7Id(context.requestPrefix() + requestId.getAndIncrement(),
                context.requestIssuerName());
    }
}
