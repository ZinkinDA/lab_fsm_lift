package com.example.project_lift_fsm.model.Controller;

import com.example.project_lift_fsm.model.Exception.MyException;
import com.example.project_lift_fsm.model.Service.FSMServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/elevator")
public class FSMConroller implements ControllerIntFSM {

    private final FSMServiceInt fsmService;


    public FSMConroller(@Autowired FSMServiceInt fsmService) {
        this.fsmService = fsmService;
    }

    @Override
    @PostMapping("/transport")
    public ResponseEntity<?> transport(@RequestBody int level) {
        try {
            this.fsmService.transportToLevel(level);
        } catch (MyException e){
            return ResponseEntity.badRequest().body("Error : " + e.getMessage());
        }
        return ResponseEntity.ok("Executed!");
    }
    @Override
    @GetMapping("/level")
    public ResponseEntity<?> getLevel(){
        return ResponseEntity.ok(fsmService.getCurrentLevel());
    }

    @Override
    @PostMapping("/turnOff")
    public ResponseEntity<?> turnOff() {
        try {
            this.fsmService.turnOff();
            return ResponseEntity.ok("OFF");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @PostMapping("/turnOn")
    public ResponseEntity<?> turnOn() {
        try {
            this.fsmService.turnOn();
            return ResponseEntity.ok("ON");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<?> getLift() {
        return ResponseEntity.ok(fsmService.getLift());
    }
}
