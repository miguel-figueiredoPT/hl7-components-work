package com.projects.producers.data.generators.context;

public record OrganizationContext(String sendingApplication,
                                  String sendingFacility,
                                  String receivingApplication,
                                  String receivingFacility,
                                  String enteringOrganization
  ) {

    public static OrganizationContext defaultOrg() {
        return new OrganizationContext("sendingApp", "sendingFacility",
                "receivingApp", "receivingFacility","enteringOrg");
    }
}
