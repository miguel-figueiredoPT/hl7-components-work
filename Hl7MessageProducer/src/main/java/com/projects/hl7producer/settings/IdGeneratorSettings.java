package com.projects.hl7producer.settings;

public record IdGeneratorSettings(String processingIdPrefix,
                                  String patientPrefix,
                                  String patientIssuerName,
                                  String requestPrefix,
                                  String requestIssuerName) {

    public static IdGeneratorSettings defaultIdGenerator() {
        return new IdGeneratorSettings(
"t",
    "pat","PAT_ISSUER",
    "req", "REQ_ISSUER"
        );
    }
}
