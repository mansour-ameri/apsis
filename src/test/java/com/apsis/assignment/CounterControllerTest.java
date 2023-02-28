package com.apsis.assignment;

import com.apsis.assignment.controller.CounterController;
import com.apsis.assignment.dtos.CounterDto;
import com.apsis.assignment.service.CounterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CounterController.class)
public class CounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CounterService service;

    @Autowired
    private ObjectMapper mapper;

    @ParameterizedTest
    @CsvSource({"newCounter,0"})
    public void givenNewCounter_thenReturn201(String counterName,int initValue) throws Exception {

        // given
        CounterDto counter = CounterDto.builder()
                .counterName(counterName)
                .initValue(initValue)
                .build();
        // when
        ResultActions response = mockMvc.perform(post("/v1/counters")
                .content(mapper.writeValueAsString(counter))
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        response.andDo(print())
                .andExpect(status().isCreated());
    }
}
