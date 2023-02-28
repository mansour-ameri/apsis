package com.apsis.assignment.service;

import com.apsis.assignment.dtos.CounterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CounterService{

    private List<CounterDto> counterDtos = new ArrayList<>();

    public CounterDto createCounter(CounterDto newCounter) throws IllegalArgumentException {
        if(isExist(newCounter)) {
            throw new IllegalArgumentException(String.format("Counter with name %s already exists.", newCounter.getCounterName()));
        }
        counterDtos.add(newCounter);
        return newCounter;
    }

    private boolean isExist(CounterDto newCounter) {
        return counterDtos.stream()
                .anyMatch(c -> c.getCounterName().equals(newCounter.getCounterName()));
    }

    public List<CounterDto> getAllCounters() {
        return counterDtos;
    }

    public Optional<CounterDto> increaseCounter(String counterName){
         return counterDtos.stream()
               .filter(c -> c.getCounterName().equals(counterName))
                 .peek(CounterDto::increaseCounter)
               .findFirst();
    }

    public Optional<CounterDto> getNamedCounter(String counterName) {
        return counterDtos.stream()
                .filter(c -> c.getCounterName().equals(counterName))
                .findFirst();
    }
}
