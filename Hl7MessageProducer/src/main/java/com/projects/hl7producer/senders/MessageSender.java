package com.projects.hl7producer.senders;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v22.message.ACK;
import com.projects.hl7producer.settings.MessageSenderSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageSender {
    private final static Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final HapiContext hapiContext = new DefaultHapiContext();
    private final MessageSenderSettings settings;

    public MessageSender(@Autowired MessageSenderSettings settings) {
        this.settings = settings;
    }

    public Message send(Message message) throws HL7Exception, LLPException, IOException {
        Connection connection = hapiContext.newClient(settings.host(), settings.port(), settings.tlsEnabled());
        Initiator initiator = connection.getInitiator();
        var response = initiator.sendAndReceive(message);

        if (response instanceof ACK) {
            var msa = ((ACK)response).getMSA();
            var ackCode = msa.getAcknowledgementCode().getValue();

            if ("AA".equals(ackCode)) {
                logger.info("Message sent successfully");
            }
            else {
                logger.warn("Unexpected acknowledgement code: {}", ackCode);
            }
        }
        else {
            logger.warn("Unexpected message class: {}", response.getClass().getSimpleName());
        }

        return response;
    }
}
