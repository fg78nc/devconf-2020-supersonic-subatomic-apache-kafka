package cz.scholz.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class ProducerAPI {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        int i = 0;

        while (i < 100)    {
            System.out.println("Sending message");
            ProducerRecord<String, String> msg = new ProducerRecord<>("my-topic", "key", "{\"time\":\"" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "\"}");
            producer.send(msg);
            i++;
            Thread.sleep(1000);
        }

        producer.close();
    }
}
