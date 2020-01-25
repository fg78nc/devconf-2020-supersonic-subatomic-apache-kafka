package cz.scholz;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class MyTimeDeserializer extends JsonbDeserializer<MyTime> {
    public MyTimeDeserializer(){
        // pass the class to the parent.
        super(MyTime.class);
    }
}
