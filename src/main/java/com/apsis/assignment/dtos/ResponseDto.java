package com.apsis.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private int totalNumberOfCounters;
    private List<CounterDto> counters;
}
