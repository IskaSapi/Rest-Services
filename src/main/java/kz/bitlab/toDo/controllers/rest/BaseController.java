package kz.bitlab.toDo.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public ResponseEntity<?> buildMessage(HttpStatus status,Object body){
        return ResponseEntity.status(status).body(body);
    }


    public ResponseEntity<?> buildMessage(HttpStatus status){
        return ResponseEntity.status(status).build();
    }
}
