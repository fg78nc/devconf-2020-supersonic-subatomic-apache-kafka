package cz.scholz.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SslConfigs;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

public class ConsumerAPI {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/Users/scholzj/development/devconf-2020-supersonic-subatomic-apache-kafka/truststore");
        props.setProperty(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "pwd123456");
        props.put("security.protocol", "SSL");
        props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "HTTPS");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Pattern.compile("my-topic"));

        int i = 0;

        while (i < 100)    {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));

            if (records.isEmpty()) {
                System.out.println("No messages received ... exiting!");
                break;
            }

            records.forEach(record -> {
                System.out.println("Received message with header " + record.key() + " and value " + record.value());

            });
        }

        consumer.close();
    }
}
