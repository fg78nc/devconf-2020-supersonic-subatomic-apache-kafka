package cz.scholz;

import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Producer {
    @Incoming("time-from-post")
    @Outgoing("produced")
    public KafkaMessage<String, MyTime> generateMessages(MyTime time) {
        return KafkaMessage.of("key", time);
    }
}
