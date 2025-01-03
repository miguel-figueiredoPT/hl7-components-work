package com.projects.hl7producer.generators;

import com.projects.hl7producer.generators.structs.Exam;
import com.projects.hl7producer.generators.structs.Patient;

public interface HospitalDataGenerator{
    Patient getNextPatient();

    Exam getNextExam(Patient patient);

    IdGenerator getIdGenerator();
}
