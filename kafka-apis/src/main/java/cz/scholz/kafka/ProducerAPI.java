package cz.scholz.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class ProducerAPI {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/Users/scholzj/development/devconf-2020-supersonic-subatomic-apache-kafka/truststore");
        props.setProperty(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "pwd123456");
        props.put("security.protocol", "SSL");
        props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "HTTPS");

        KafkaProducer<String, MyTime> producer = new KafkaProducer<String, MyTime>(props, new StringSerializer(), new MyTimeSerializer());

        int i = 0;

        while (i < 100)    {
            System.out.println("Sending message");
            ProducerRecord<String, MyTime> msg = new ProducerRecord<>("my-topic", "key", MyTime.create(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())));
            producer.send(msg);
            i++;
            Thread.sleep(1000);
        }

        producer.close();
    }
}
