package com.projects;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v22.message.ACK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class MessageSender {
    Logger logger = LoggerFactory.getLogger(MessageSender.class);

    HapiContext context = new DefaultHapiContext();

    private final String host;
    private final int port;
    private final boolean tlsEnabled;

    public MessageSender(String host, int port, boolean tlsEnabled) {
        this.host = host;
        this.port = port;
        this.tlsEnabled = tlsEnabled;
    }

    public Message send(Message message) throws HL7Exception, LLPException, IOException {
        Connection connection = context.newClient(host, port, tlsEnabled);
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
