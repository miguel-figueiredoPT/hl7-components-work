package com.projects.producers.data.generators;

import com.projects.producers.data.HL7Id;
import com.projects.producers.data.generators.context.IdGeneratorContext;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultIdGenerator implements IdGenerator{
    private final IdGeneratorContext context;
    private final AtomicInteger processingId = new AtomicInteger(1);
    private final AtomicInteger patientId = new AtomicInteger(1);
    private final AtomicInteger requestId = new AtomicInteger(1);

    public DefaultIdGenerator(IdGeneratorContext context) {
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
