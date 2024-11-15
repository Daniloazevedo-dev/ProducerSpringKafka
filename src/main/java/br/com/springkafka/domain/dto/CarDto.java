package br.com.springkafka.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CarDto {
    private String id;
    private String name;
    private String brand;
}
