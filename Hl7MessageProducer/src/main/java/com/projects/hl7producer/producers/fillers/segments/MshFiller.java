package com.projects.hl7producer.producers.fillers.segments;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.*;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;

public interface MshFiller {

    default void fillMSH(MSH msh, HospitalDataGeneratorSettings hospitalDataGeneratorSettings) throws DataTypeException {
        msh.getMsh3_SendingApplication().setValue(hospitalDataGeneratorSettings.organizationSettings().enteringOrganization());
        msh.getMsh4_SendingFacility().setValue(hospitalDataGeneratorSettings.organizationSettings().enteringOrganization());
        msh.getMsh5_ReceivingApplication().setValue(hospitalDataGeneratorSettings.organizationSettings().enteringOrganization());
        msh.getMsh6_ReceivingFacility().setValue(hospitalDataGeneratorSettings.organizationSettings().enteringOrganization());
    }
}
