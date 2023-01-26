package com.example.project_lift_fsm.model.Service;

import com.example.project_lift_fsm.model.FSM;

public interface FSMServiceInt {

    void transportToLevel(int level);

    int getCurrentLevel();

    void turnOff();

    void turnOn();

    FSM getLift();

}
