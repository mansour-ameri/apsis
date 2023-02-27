package com.apsis.assignment.service;

import com.apsis.assignment.dtos.CounterDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CounterService {

    private List<CounterDto> counterDtos = new ArrayList<>();

    public CounterDto createCounter(CounterDto request) {
        counterDtos.add(request);
        return request;
    }


    public List<CounterDto> getAllCounters() {
        return counterDtos;
    }

    public CounterDto increaseCounter(String counterName){
         return counterDtos.stream()
               .filter(c -> c.getCounterName().equals(counterName))
                 .peek(CounterDto::increaseCounter)
               .findFirst()
                 .orElseThrow(() -> new NoSuchElementException());
    }

    public CounterDto getNamedCounter(String counterName) {
        return counterDtos.stream()
                .filter(c -> c.getCounterName().equals(counterName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
