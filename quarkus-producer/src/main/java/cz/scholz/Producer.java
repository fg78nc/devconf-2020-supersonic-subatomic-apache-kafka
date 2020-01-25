package cz.scholz;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class Producer {
    @Outgoing("produced")
    public Flowable<KafkaMessage<String, MyTime>> generateMessages() {
        return Flowable.interval(1, TimeUnit.SECONDS)
                .map(tick -> KafkaMessage.of("key", MyTime.create(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()))));
    }
}
