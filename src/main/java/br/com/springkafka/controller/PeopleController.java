package br.com.springkafka.controller;

import br.com.springkafka.People;
import br.com.springkafka.domain.dto.PeopleDTO;
import br.com.springkafka.producer.PeopleProducer;
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
@RequestMapping("/peoples")
public class PeopleController {

    private final PeopleProducer peopleProducer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> send(@RequestBody PeopleDTO peopleDTO) {

        var id = UUID.randomUUID().toString();

        var message = People.newBuilder()
                .setId(id)
                .setName(peopleDTO.getName())
                .setCpf(peopleDTO.getCpf())
                .setBooks(peopleDTO.getBooks().stream().map( p-> (CharSequence) p).toList())
                .build();

        peopleProducer.sendMessage(message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
