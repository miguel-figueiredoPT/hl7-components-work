package com.projects.hl7producer.generators;

import com.projects.hl7producer.generators.structs.Exam;
import com.projects.hl7producer.generators.structs.Patient;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HospitalDataGeneratorImpl implements HospitalDataGenerator {
    private final RandomPicker randomPicker;
    private final HospitalDataGeneratorSettings hospitalDataGeneratorSettings;
    private final IdGenerator idGenerator;

    public HospitalDataGeneratorImpl(@Autowired RandomPicker randomPicker,
                                     @Autowired HospitalDataGeneratorSettings hospitalDataGeneratorSettings,
                                     @Autowired IdGenerator idGenerator) {
        this.randomPicker = randomPicker;
        this.hospitalDataGeneratorSettings = hospitalDataGeneratorSettings;
        this.idGenerator = idGenerator;
    }

    public Patient getNextPatient() {
        return new Patient(
                idGenerator.getNextPatientId(),
                randomPicker.getFirstName(),
                randomPicker.getLastName(),
                randomPicker.getLastName(),
                randomPicker.getSex(),
                randomPicker.getAddress()
        );
    }

    public Exam getNextExam(Patient patient) {
        return new Exam(
                patient,
                idGenerator.getNextExamId(),
                "NW",
                new Date(),
                hospitalDataGeneratorSettings.organizationSettings().enteringOrganization()
        );
    }

    @Override
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }
}
