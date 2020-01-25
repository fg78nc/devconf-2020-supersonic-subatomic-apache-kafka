package cz.scholz;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class Producer {
    @Incoming("time-from-post")
    @Outgoing("produced")
    public KafkaMessage<String, MyTime> generateMessages(MyTime time) {
        return KafkaMessage.of("key", time);
    }
}
