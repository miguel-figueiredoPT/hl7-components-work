package com.projects.hl7producer.producers.fillers.segments;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.PID;
import com.projects.hl7producer.generators.structs.Patient;

public interface PidFiller {

    default void fillPid(PID pid, Patient patient) throws DataTypeException {
        var patName = pid.getPatientName();
        patName.getFamilyName().setValue(patient.lastName());
        patName.getGivenName().setValue(patient.firstName());
        patName.getMiddleInitialOrName().setValue(patient.middleName());

        // PIDs
        pid.getPid2_PatientIDExternalID().getCk1_IDNumber().setValue(patient.patientId().id());
        pid.getPid2_PatientIDExternalID().getCk4_FacilityID().setValue(patient.patientId().assigningAuthorityNamespace());
        pid.getPid4_AlternatePatientID().setValue(patient.patientId().id());

        // Address
        pid.getPid11_PatientAddress(0).getAd1_StreetAddress().setValue(patient.patientAddress().streetName());
        pid.getPid11_PatientAddress(0).getAd3_City().setValue(patient.patientAddress().city());
        pid.getPid11_PatientAddress(0).getAd6_Country().setValue(patient.patientAddress().country());
    }
}
