package cz.scholz.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class MyTimeDeserializer implements Deserializer {
    @Override
    public void configure(Map map, boolean b) {
        // Nothing to do
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        MyTime obj = null;

        try {
            obj = mapper.readValue(bytes, MyTime.class);
        } catch (Exception e) {
            System.out.println("Ups: " + e);
        }

        return obj;
    }

    @Override
    public void close() {
        // Nothing to close
    }
}
