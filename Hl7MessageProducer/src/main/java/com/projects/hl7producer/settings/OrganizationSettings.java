package com.projects.hl7producer.settings;

public record OrganizationSettings(String sendingApplication,
                                   String sendingFacility,
                                   String receivingApplication,
                                   String receivingFacility,
                                   String enteringOrganization
  ) {

    public static OrganizationSettings defaultOrg() {
        return new OrganizationSettings("sendingApp", "sendingFacility",
                "receivingApp", "receivingFacility","enteringOrg");
    }
}
