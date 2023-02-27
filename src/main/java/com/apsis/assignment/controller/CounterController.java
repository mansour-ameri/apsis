package com.apsis.assignment.controller;

import com.apsis.assignment.dtos.CounterDto;
import com.apsis.assignment.service.CounterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<CounterDto> createCounter(@Valid @RequestBody CounterDto request){
        //TODO: check if element exist
        return  ResponseEntity.ok().body(counterService.createCounter(request));
    }

    @GetMapping
    public ResponseEntity<List<CounterDto>> getAllCounters(){
        return ResponseEntity.ok().body(counterService.getAllCounters());
    }

    @GetMapping("/{counterName}")
    public ResponseEntity<CounterDto> getNamedCounter(@PathVariable String counterName){
        return ResponseEntity.ok().body(counterService.getNamedCounter(counterName));
    }

    @PutMapping("/{counterName}")
    public ResponseEntity<CounterDto> increaseCounter(@PathVariable String counterName){
        return ResponseEntity.ok().body(counterService.increaseCounter(counterName));
    }
}
