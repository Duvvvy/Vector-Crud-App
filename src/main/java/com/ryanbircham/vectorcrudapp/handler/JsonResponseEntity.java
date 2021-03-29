package com.ryanbircham.vectorcrudapp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponseEntity {
    private ResponseEntity<Object> response;

    public JsonResponseEntity(String message, HttpStatus httpStatus){
        String JsonBody =
                "{\n" +
                "  \"code\": \"" + httpStatus + "\",\n" +
                "  \"message\": \""+ message +"\"\n" +
                "}";;
        this.response =  new ResponseEntity(JsonBody, httpStatus);
    }

    public ResponseEntity<Object> getResponse() {
        return response;
    }
}
