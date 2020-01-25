package cz.scholz;

import io.quarkus.kafka.client.serialization.JsonbSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Streams {
    @Produces
    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        JsonbSerde<MyTime> myTimeSerde = new JsonbSerde<>(MyTime.class);
        JsonbSerde<MyDate> myDateSerde = new JsonbSerde<>(MyDate.class);

        builder.stream("my-topic", Consumed.with(Serdes.String(), myTimeSerde))
                .mapValues(value -> MyDate.fromMyTime(value))
                .to("my-topic2", Produced.with(Serdes.String(), myDateSerde));

        return builder.build();
    }
}
