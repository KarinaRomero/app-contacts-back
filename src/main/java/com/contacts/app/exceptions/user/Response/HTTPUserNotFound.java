package com.contacts.app.exceptions.user.Response;

import com.contacts.app.exceptions.user.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Information is not allowed")
public class HTTPUserNotFound extends UserNotFound {
    private HttpStatus status;
    public HTTPUserNotFound(String message) {
        super(message);
        status = HttpStatus.BAD_REQUEST;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
