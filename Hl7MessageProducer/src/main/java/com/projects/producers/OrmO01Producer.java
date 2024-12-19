package com.projects.producers;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v22.message.ORM_O01;
import com.projects.producers.data.Exam;
import com.projects.producers.data.generators.context.HospitalDataContext;
import com.projects.producers.data.generators.IdGenerator;
import com.projects.producers.fillers.OrmO01SegmentFiller;

import java.io.IOException;

public class OrmO01Producer implements MessageProducer<ORM_O01, Exam>, OrmO01SegmentFiller {
    private final HospitalDataContext hospitalDataContext;
    private final IdGenerator idGenerator;

    public OrmO01Producer(HospitalDataContext hospitalDataContext, IdGenerator idGenerator) {
        this.hospitalDataContext = hospitalDataContext;
        this.idGenerator = idGenerator;
    }

    @Override
    public ORM_O01 produce(Exam record) throws HL7Exception, IOException {
        ORM_O01 message = new ORM_O01();
        message.initQuickstart("ORM", "O01", idGenerator.getNextProcessingId());

        var pid = message.getPATIENT().getPID();
        var order = message.getORDER();
        var orc = order.getORC();
        var obr = order.getORDER_DETAIL().getOBR();

        fillMSH(message.getMSH(), hospitalDataContext);
        fillPid(pid, record.patient());
        fillOrc(orc, record);
        fillObr(obr, record);

        return message;
    }
}
