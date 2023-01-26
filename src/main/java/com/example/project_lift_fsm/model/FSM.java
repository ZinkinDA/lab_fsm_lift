package com.example.project_lift_fsm.model;

import com.example.project_lift_fsm.model.Enum.Door;
import com.example.project_lift_fsm.model.Enum.Event;
import com.example.project_lift_fsm.model.Enum.State;
import com.example.project_lift_fsm.model.Exception.MyException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FSM {
    @JsonIgnore
    private static FSM fsm;
    @JsonView
    private Door door = Door.is_CLOSED;
    @JsonIgnore
    @Value(value = "${fsm.ID}")
    private int ID = 999;
    @Value(value = "${fsm.maxLevel}")
    @JsonView
    private int maxLevel = 9;
    @JsonView
    private int currentLevel = 1;// Это пока что.
    @JsonView
    private State state;

    private FSM(){

    }

    private FSM(int currentLevel, State state) {
        this.currentLevel = currentLevel;
        this.state = state;
    }

    public static FSM getInstance(){
        if(fsm == null){
            return new FSM(1,State.WAIT);
        }
        return fsm;
    }

    public int getID() {
        return ID;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void getStep(int level) throws InterruptedException {
        switch (Event.GO_READ_VALUE){
            case GO_READ_VALUE :
                if(this.state == State.WAIT){
                    this.state = State.READ_VALUE;
                    System.out.println("Reading Data : " + state);
                    Thread.sleep(500);
                } else {
                    throw new RuntimeException("FSM is not READY!");
                }
            case GO_PREPARATION :
                if(this.state == State.READ_VALUE){
                    this.state = State.PREPARATION;
                    System.out.println("Status : " + state);
                    Thread.sleep(1000);
                } else {
                    throw new RuntimeException("FSM is not READY!");
                }
            case GO_TRANS_TO_THE_LEVEL :
                if (this.state == State.PREPARATION || this.door.equals(Door.is_CLOSED)) {
                        this.state = State.WORKED;
                    System.out.println("worked : " + state);
                    if(currentLevel < level){
                        while (currentLevel != level){
                            currentLevel++;
                            System.out.println(currentLevel);
                            Thread.sleep(1500);
                        }
                        door = Door.is_OPEN;
                        System.out.println("Open the door");
                        Thread.sleep(2000);
                        door = Door.is_CLOSED;
                        System.out.println("Close the door");
                    } else if(currentLevel == level) {
                        door = Door.is_OPEN;
                        System.out.println("Open the door");
                        Thread.sleep(2000);
                        door = Door.is_CLOSED;
                        System.out.println("Close the door");
                    } else {
                        while (currentLevel != level){
                            currentLevel--;
                            System.out.println(currentLevel);
                            Thread.sleep(1500);
                        }
                    }
                } else {
                    throw new RuntimeException("FSM is not READY!");
                }
            case AWAIT :
                if(this.state == State.WORKED){
                    this.state = State.WAIT;
                    System.out.println("Statement : " + state);
                } else {
                    throw new RuntimeException("FSM is not READY!");
                }
                break;
        }
    }
    public void getStep(Event event){
        switch (event) {
            case GO_OFF:
                if (this.state == State.WAIT) {
                    this.state = State.OFF;
                    System.out.println("FSM TURNED OFF!");
                } else {
                    throw new RuntimeException("FSM is not READY!");
                }
                break;
            case GO_ON:
                if (this.state == State.OFF){
                    this.state = State.WAIT;
                    System.out.println("FSM ON!");
                } else {
                    throw new RuntimeException("FSM is not READY!");
                }
                break;
        }
    }

}
