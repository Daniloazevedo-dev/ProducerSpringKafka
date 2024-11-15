package br.com.springkafka.controller;

import br.com.springkafka.Car;
import br.com.springkafka.domain.dto.CarDto;
import br.com.springkafka.producer.CarProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarProducer carPoducer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendMessage(@RequestBody CarDto carDto) {
        var id = UUID.randomUUID().toString();

        var message = Car.newBuilder()
                .setId(id)
                .setName(carDto.getName())
                .setBrand(carDto.getBrand())
                .build();

        carPoducer.sendMessage(message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
