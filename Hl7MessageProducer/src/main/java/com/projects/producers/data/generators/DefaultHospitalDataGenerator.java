package com.projects.producers.data.generators;

import com.projects.producers.data.Exam;
import com.projects.producers.data.HL7Id;
import com.projects.producers.data.Patient;
import com.projects.producers.data.generators.context.HospitalDataContext;

import java.util.Date;

public class DefaultHospitalDataGenerator implements HospitalDataGenerator {
    private final Randoms randoms;
    private final HospitalDataContext hospitalDataContext;
    private final IdGenerator idGenerator;

    public DefaultHospitalDataGenerator(Randoms randoms, HospitalDataContext hospitalDataContext, IdGenerator idGenerator) {
        this.randoms = randoms;
        this.hospitalDataContext = hospitalDataContext;
        this.idGenerator = idGenerator;
    }

    public Patient getNextPatient() {
        return new Patient(
                idGenerator.getNextPatientId(),
                randoms.getFirstName(),
                randoms.getLastName(),
                randoms.getLastName(),
                randoms.getSex(),
                randoms.getAddress()
        );
    }

    public Exam getNextExam(Patient patient) {
        return new Exam(
                patient,
                idGenerator.getNextExamId(),
                "NW",
                new Date(),
                hospitalDataContext.organizationContext().enteringOrganization()
        );
    }

    @Override
    public String getNextProcessingId() {
        return idGenerator.getNextProcessingId();
    }

    @Override
    public HL7Id getNextPatientId() {
        return idGenerator.getNextPatientId();
    }

    @Override
    public HL7Id getNextExamId() {
        return idGenerator.getNextExamId();
    }
}
