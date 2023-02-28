package com.apsis.assignment.controller;

import com.apsis.assignment.dtos.CounterDto;
import com.apsis.assignment.dtos.ResponseDto;
import com.apsis.assignment.service.CounterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/counters")
@OpenAPIDefinition(
        info =
        @Info(
                title = "Apsis RnD Java assignment API",
                version = "1.0",
                description =
                        "Apsis RnD Java assignment"))
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService){
        this.counterService = counterService;
    }

    @PostMapping
    public ResponseEntity<CounterDto> createCounter(@Valid @RequestBody CounterDto counter){
        return  ResponseEntity.status(HttpStatus.CREATED).body(counterService.createCounter(counter));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllCounters(){
        return ResponseEntity.ok().body(ResponseDto.builder()
                .totalNumberOfCounters(counterService.getAllCounters().size())
                .counters(counterService.getAllCounters()).build());
    }

    @GetMapping("/{counterName}")
    public ResponseEntity<CounterDto> getNamedCounter(@PathVariable String counterName){
        return ResponseEntity.ok().body(counterService.getNamedCounter(counterName)
                .orElseThrow(() -> new NoSuchElementException(String.format("Counter with name %s not exist", counterName))));
    }

    @PutMapping("/{counterName}")
    public ResponseEntity<CounterDto> increaseCounter(@PathVariable String counterName){
        return ResponseEntity.ok().body(counterService.increaseCounter(counterName)
                .orElseThrow(() -> new NoSuchElementException(String.format("Counter with name %s not exist", counterName))));
    }
}
