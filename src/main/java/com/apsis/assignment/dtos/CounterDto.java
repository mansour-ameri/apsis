package com.apsis.assignment.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
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
