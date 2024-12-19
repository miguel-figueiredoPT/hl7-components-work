package com.projects;

import com.projects.producers.data.generators.Randoms;
import com.projects.producers.data.generators.DefaultHospitalDataGenerator;
import com.projects.producers.data.generators.DefaultIdGenerator;
import com.projects.producers.data.generators.HospitalDataGenerator;
import com.projects.producers.data.generators.IdGenerator;
import com.projects.producers.AdtA01Producer;
import com.projects.producers.OrmO01Producer;
import com.projects.producers.data.generators.context.HospitalDataContext;
import com.projects.producers.data.generators.context.IdGeneratorContext;
import com.projects.producers.data.generators.context.OrganizationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MessageSender messageSender(@Value("${hl7Host:localhost}") String host,
                                       @Value("${hl7Port:6661}") Integer port) {

        return new MessageSender(host,port, false);
    }

    @Bean
    public HospitalDataContext context(@Value("${sendingOrg:SENDING_ORG}") String sendingOrg,
                                       @Value("${sendingFacility:SENDING_FACILITY}") String sendingFacility,
                                       @Value("${receivingOrg:RECEIVING_ORG}") String receivingOrg,
                                       @Value("${receivingFacility:RECEIVING_FACILITY}") String receivingFacility,
                                       @Value("${enteringOrg:ENTERING_ORG}") String enteringOrg

    ) {
        return  new HospitalDataContext(
                new OrganizationContext(sendingOrg, sendingFacility, receivingOrg, receivingFacility, enteringOrg)
        );
    }

    @Bean
    public IdGeneratorContext idGeneratorContext(@Value("${processingIdPrefix:t}") String processingIdPrefix,
                                                 @Value("${patIdPrefix:pat}") String patIdPrefix,
                                                 @Value("${patIdIssuer:PAT_ISSUER}") String patIdIssuer,
                                                 @Value("${reqIdPrefix:req}") String reqIdPrefix,
                                                 @Value("${reqIdIssuer:REQ_ISSUER}") String reqIdIssuer
    ) {
        return new IdGeneratorContext(processingIdPrefix, patIdPrefix,
                patIdIssuer,
                reqIdPrefix, reqIdIssuer);
    }

    @Bean
    public IdGenerator idGenerator(@Autowired IdGeneratorContext idGeneratorContext) {
        return new DefaultIdGenerator(idGeneratorContext);
    }

    @Bean
    public HospitalDataGenerator hospitalDataGenerator(@Autowired HospitalDataContext hospitalDataContext, @Autowired IdGenerator idGenerator) {
        return new DefaultHospitalDataGenerator(new Randoms(), hospitalDataContext, idGenerator);
    }

    @Bean
    public AdtA01Producer adtA01Producer(@Autowired HospitalDataContext hospitalDataContext, @Autowired IdGenerator idGenerator) {
        return new AdtA01Producer(hospitalDataContext, idGenerator);
    }

    @Bean
    public OrmO01Producer ormO01Producer(@Autowired HospitalDataContext hospitalDataContext, @Autowired IdGenerator idGenerator) {
        return new OrmO01Producer(hospitalDataContext, idGenerator);
    }

}