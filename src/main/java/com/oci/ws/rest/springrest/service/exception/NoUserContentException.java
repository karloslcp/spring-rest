package com.oci.ws.rest.springrest.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoUserContentException extends RuntimeException
{
    public NoUserContentException(final String message)
    {
        super(message);
    }
}
