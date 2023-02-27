package com.apsis.assignment.service;

import com.apsis.assignment.dtos.CounterDto;

import java.util.List;
import java.util.Optional;

public interface ICounterService {
    public CounterDto createCounter(CounterDto newCounter) throws IllegalArgumentException;

    public List<CounterDto> getAllCounters();

    public Optional<CounterDto> increaseCounter(String counterName);

    public Optional<CounterDto> getNamedCounter(String counterName);

}
