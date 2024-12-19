package com.projects.producers.data.generators;

import com.projects.producers.data.Exam;
import com.projects.producers.data.Patient;

public interface HospitalDataGenerator extends IdGenerator {
    Patient getNextPatient();

    Exam getNextExam(Patient patient);
}
