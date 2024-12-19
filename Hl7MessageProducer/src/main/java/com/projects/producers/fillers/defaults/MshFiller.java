package com.projects.producers.fillers.defaults;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.segment.*;
import com.projects.producers.data.generators.context.HospitalDataContext;

public interface MshFiller {

    default void fillMSH(MSH msh, HospitalDataContext hospitalDataContext) throws DataTypeException {
        msh.getMsh3_SendingApplication().setValue(hospitalDataContext.organizationContext().enteringOrganization());
        msh.getMsh4_SendingFacility().setValue(hospitalDataContext.organizationContext().enteringOrganization());
        msh.getMsh5_ReceivingApplication().setValue(hospitalDataContext.organizationContext().enteringOrganization());
        msh.getMsh6_ReceivingFacility().setValue(hospitalDataContext.organizationContext().enteringOrganization());
    }
}
