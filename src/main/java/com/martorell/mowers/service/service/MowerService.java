package com.martorell.mowers.service.service;

import com.martorell.mowers.service.service.exceptions.MowerRequestException;

public interface MowerService {

    String getMowerLocation(String position, String movement) throws MowerRequestException;
}
