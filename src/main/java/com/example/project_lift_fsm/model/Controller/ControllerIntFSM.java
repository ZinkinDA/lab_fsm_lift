package com.example.project_lift_fsm.model.Controller;

import org.springframework.http.ResponseEntity;

public interface ControllerIntFSM {

    ResponseEntity<?> transport(int level);

    ResponseEntity<?> getLevel();

    ResponseEntity<?> turnOff();

    ResponseEntity<?> turnOn();

    ResponseEntity<?> getLift();

}
