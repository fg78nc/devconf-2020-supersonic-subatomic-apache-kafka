package cz.scholz;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class Consumer {
    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class.getName());

    @Incoming("consumed")
    @Outgoing("new-times")
    @Broadcast
    public MyTime process(KafkaMessage<String, MyTime> msg) {
        LOG.info("Received message (topic: {}, partition: {}) with key {}: {}", msg.getTopic(), msg.getPartition(), msg.getKey(), msg.getPayload());
        msg.ack();
        return msg.getPayload();
    }
}
