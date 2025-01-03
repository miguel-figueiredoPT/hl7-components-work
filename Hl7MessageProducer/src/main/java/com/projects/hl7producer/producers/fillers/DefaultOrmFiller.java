package com.projects.hl7producer.producers.fillers;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v22.message.ORM_O01;
import com.projects.hl7producer.settings.HospitalDataGeneratorSettings;
import com.projects.hl7producer.generators.structs.Exam;
import com.projects.hl7producer.producers.fillers.segments.*;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrmFiller implements MessageSegmentFiller<ORM_O01, Exam>, MshFiller, PidFiller, Pv1Filler, OrcFiller, ObrFiller {
    public void fill(ORM_O01 message, Exam record, HospitalDataGeneratorSettings context) throws DataTypeException {
        var pid = message.getPATIENT().getPID();
        var order = message.getORDER();
        var orc = order.getORC();
        var obr = order.getORDER_DETAIL().getOBR();

        fillMSH(message.getMSH(), context);
        fillPid(pid, record.patient());
        fillOrc(orc, record);
        fillObr(obr, record);
    }
}
