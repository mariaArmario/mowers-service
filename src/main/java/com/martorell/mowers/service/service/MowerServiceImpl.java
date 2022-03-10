package com.martorell.mowers.service.service;

import com.martorell.mowers.service.database.entity.TwistLetters;
import com.martorell.mowers.service.database.repository.TwistLettersRepository;
import com.martorell.mowers.service.enums.Directions;
import com.martorell.mowers.service.service.exceptions.MowerRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MowerServiceImpl implements MowerService {

    @Autowired
    private TwistLettersRepository twistLettersRepository;

    /**
     * This method get mower location based on it position and movement
     * @param position
     * @param movement
     * @return
     */
    @Override
    public String getMowerLocation(String position, String movement) throws MowerRequestException {

        List<TwistLetters> twistLetters = twistLettersRepository.findAll();

        List<String> cardinalOrder = twistLetters.size() == 1 ? Arrays.asList(twistLetters.get(0).getCardinalOrder().split(",")) : new ArrayList<>();

        String[] initialPositions = position.split("");

        String cardinalPosition = initialPositions[2];
        Integer xCoordinate = Integer.valueOf(initialPositions[0]);
        Integer yCoordinate = Integer.valueOf(initialPositions[1]);

        validatePositions(cardinalPosition, xCoordinate, yCoordinate, cardinalOrder);

        for(String letter : movement.split("")) {

            if (letter.equals(Directions.R.toString())) {
                cardinalPosition = cardinalOrder.size() <= cardinalOrder.indexOf(cardinalPosition) + 1 ? cardinalOrder.get(0) : cardinalOrder.get(cardinalOrder.indexOf(cardinalPosition) + 1);
            } else if (letter.equals(Directions.L.toString())) {
                cardinalPosition = cardinalOrder.indexOf(cardinalPosition) == 0 ? cardinalOrder.get(cardinalOrder.size() - 1) : cardinalOrder.get(cardinalOrder.indexOf(cardinalPosition) - 1);
            } else if (letter.equals(Directions.M.toString())) {

                switch (cardinalPosition) {
                    case "N": yCoordinate = yCoordinate + 1;
                        break;
                    case "S": yCoordinate = yCoordinate - 1;
                        break;
                    case "E": xCoordinate = xCoordinate + 1;
                        break;
                    case "W": xCoordinate = xCoordinate - 1;
                        break;
                }
            } else {
                throw new MowerRequestException("Movement", "Error getting mower location", "Letters of movement must be R, L or M", HttpStatus.BAD_REQUEST);
            }
        }

        if(xCoordinate < 0 | yCoordinate < 0 )
            throw new MowerRequestException("Movement", "Error getting mower location", "Mower is out of plateau", HttpStatus.BAD_REQUEST);

        return xCoordinate.toString() + yCoordinate.toString() + cardinalPosition;
    }

    /**
     * This method checks if position parameter is valid and if exists cardinal order in database
     * @param cardinalPosition
     * @param xCoordinate
     * @param yCoordinate
     * @param cardinalOrder
     */
    private void validatePositions(String cardinalPosition, Integer xCoordinate, Integer yCoordinate, List<String> cardinalOrder) throws MowerRequestException {

        if(cardinalOrder.isEmpty())
            throw new MowerRequestException("Movement", "Error getting mower location", "The cardinal order could not be obtained from database", HttpStatus.UNPROCESSABLE_ENTITY);

        if(!cardinalOrder.contains(cardinalPosition))
            throw new MowerRequestException("Movement", "Error getting mower location", "Cardinal points must be N, S, E, W", HttpStatus.BAD_REQUEST);

        if(xCoordinate < 0 | yCoordinate < 0 )
            throw new MowerRequestException("Movement", "Error getting mower location", "Mower is out of plateau", HttpStatus.BAD_REQUEST);

    }

}
