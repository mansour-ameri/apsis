package com.apsis.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CounterDto {
    private UUID id;

    @NotNull
    private String counterName;
    private int initValue;

    public CounterDto(){
        this.id = UUID.randomUUID();
    }

    public void increaseCounter(){
        this.initValue++;
    }
}
