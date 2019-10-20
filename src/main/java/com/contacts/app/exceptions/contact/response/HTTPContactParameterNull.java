package com.contacts.app.exceptions.contact.response;

import com.contacts.app.exceptions.contact.ContactParameterNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Contact is not completed")
public class HTTPContactParameterNull extends ContactParameterNull {
    private HttpStatus status;
    public HTTPContactParameterNull(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
