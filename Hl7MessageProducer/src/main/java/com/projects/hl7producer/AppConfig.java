package com.projects.hl7producer;

import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;
import com.projects.hl7producer.settings.IdGeneratorSettings;
import com.projects.hl7producer.settings.MessageSenderSettings;
import com.projects.hl7producer.settings.OrganizationSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.random.RandomGenerator;

@Configuration
public class AppConfig {
    @Bean
    public MessageSenderSettings messageSenderSettings(@Value("${hl7Host:localhost}") String host,
                                                      @Value("${hl7Port:6661}") Integer port,
                                                      @Value("${tlsEnabled:false}") Boolean tlsEnabled) {
        return new MessageSenderSettings(host,port, tlsEnabled);
    }

    @Bean
    public HospitalDataGeneratorSettings hospitalDataGeneratorSettings(@Value("${sendingOrg:SENDING_ORG}") String sendingOrg,
                                                             @Value("${sendingFacility:SENDING_FACILITY}") String sendingFacility,
                                                             @Value("${receivingOrg:RECEIVING_ORG}") String receivingOrg,
                                                             @Value("${receivingFacility:RECEIVING_FACILITY}") String receivingFacility,
                                                             @Value("${enteringOrg:ENTERING_ORG}") String enteringOrg

    ) {
        return  new HospitalDataGeneratorSettings(
                new OrganizationSettings(sendingOrg, sendingFacility, receivingOrg, receivingFacility, enteringOrg)
        );
    }

    @Bean
    public IdGeneratorSettings idGeneratorSettings(@Value("${processingIdPrefix:t}") String processingIdPrefix,
                                                  @Value("${patIdPrefix:pat}") String patIdPrefix,
                                                  @Value("${patIdIssuer:PAT_ISSUER}") String patIdIssuer,
                                                  @Value("${reqIdPrefix:req}") String reqIdPrefix,
                                                  @Value("${reqIdIssuer:REQ_ISSUER}") String reqIdIssuer
    ) {
        return new IdGeneratorSettings(processingIdPrefix, patIdPrefix,
                patIdIssuer,
                reqIdPrefix, reqIdIssuer);
    }

    @Bean
    public RandomGenerator randomGenerator(@Value("${randomSeed:-1}") Integer seed) {
        return seed == -1 ? new Random() : new Random(seed);
    }
}