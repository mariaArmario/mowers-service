package com.martorell.mowers.service.controller;

import com.martorell.mowers.service.database.entity.TwistLetters;
import com.martorell.mowers.service.service.MowerServiceImpl;
import com.martorell.mowers.service.service.exceptions.MowerRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MowerControllerTest {

    @InjectMocks
    MowerController mowerController;

    @Mock
    private MowerServiceImpl mowerService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mowerController)
                .build();
    }

    @Test
    public void getMowerLocation_ok() throws Exception {

        // GIVEN
        String position = "12N";
        String movement = "LMLMLMLMM";

        when(mowerService.getMowerLocation(position, movement)).thenReturn("13N");

        // THEN
        mockMvc.perform(get("/mowers/location")
                .queryParam("position",position)
                .queryParam("movement", movement))
                .andExpect(status().isOk());
    }

    @Test
    public void getMowerLocation_badRequest() throws Exception {

        // GIVEN
        MowerRequestException exception = new MowerRequestException("Movement", "Error getting mower location", "Cardinal points must be N, S, E, W", HttpStatus.BAD_REQUEST);
        String position = "12X";
        String movement = "LMLMLMLMM";

        when(mowerService.getMowerLocation(position, movement)).thenThrow(exception);

        // THEN
        mockMvc.perform(get("/mowers/location")
                .queryParam("position",position)
                .queryParam("movement", movement))
                .andExpect(status().isBadRequest());
    }
}
