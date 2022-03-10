package com.martorell.mowers.service.service;

import com.martorell.mowers.service.database.entity.TwistLetters;
import com.martorell.mowers.service.database.repository.TwistLettersRepository;
import com.martorell.mowers.service.service.exceptions.MowerRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MowerServiceImplTest {

    @InjectMocks
    MowerServiceImpl mowerService;

    @Mock
    private TwistLettersRepository twistLettersRepository;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getMowerLocation_ok() throws MowerRequestException {

        // GIVEN
        String position = "12N";
        String movement = "LMLMLMLMM";

        List<TwistLetters> twistLetters = new ArrayList<>();
        TwistLetters twistLetter = new TwistLetters(1,"N,E,S,W");
        twistLetters.add(twistLetter);

        when(twistLettersRepository.findAll()).thenReturn(twistLetters);

        // WHEN
        String result = mowerService.getMowerLocation(position, movement);

        // THEN
        assertEquals(result, "13N");
    }

}
