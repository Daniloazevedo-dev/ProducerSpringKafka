package br.com.springkafka.producer;

import br.com.springkafka.Car;
import br.com.springkafka.People;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Car> kafkaTemplate;

    public void sendMessage(Car car) {
        try {
            kafkaTemplate.send(topicName, (String) car.getId(), car);
            log.info("Mensagem enviada: {}", car);
        } catch (Exception e) {
            log.error("Erro ao enviar mensagem: {}", e.getMessage());
        }
    }
}
