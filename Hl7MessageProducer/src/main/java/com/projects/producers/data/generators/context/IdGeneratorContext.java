package com.projects.producers.data.generators.context;

public record IdGeneratorContext(String processingIdPrefix,
                                  String patientPrefix,
                                  String patientIssuerName,
                                  String requestPrefix,
                                  String requestIssuerName) {

    public static IdGeneratorContext defaultIdGenerator() {
        return new IdGeneratorContext(
"t",
    "pat","PAT_ISSUER",
    "req", "REQ_ISSUER"
        );
    }
}
