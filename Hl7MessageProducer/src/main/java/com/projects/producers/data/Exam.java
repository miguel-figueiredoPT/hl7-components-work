package com.projects.producers.data;

import java.util.Date;

public record Exam(Patient patient, HL7Id requestId, String status, Date examDate,
           String enteringOrganization) {
}
