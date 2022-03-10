package com.martorell.mowers.service.service.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public final class MowerRequestException extends Exception{

    private final String entityName;
    private final String errorKey;
    private final String defaultMessage;
    private final HttpStatus status;

	public MowerRequestException(String entityName, String errorKey, String defaultMessage, HttpStatus status) {
        super(defaultMessage);
        this.entityName = entityName;
        this.errorKey = errorKey;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }

}
