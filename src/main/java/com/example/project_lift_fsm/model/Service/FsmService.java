package com.example.project_lift_fsm.model.Service;

import com.example.project_lift_fsm.model.Enum.Event;
import com.example.project_lift_fsm.model.Exception.MyException;
import com.example.project_lift_fsm.model.FSM;
import org.springframework.stereotype.Service;

@Service
public class FsmService implements FSMServiceInt {
    private final FSM lift = FSM.getInstance();

    @Override
    public void transportToLevel(int level) {
        if(level > lift.getMaxLevel() || level <= 0){
            throw new MyException("level > maxLevel or level < 1\n" + level + " > " + lift.getMaxLevel());
        }

        try {
            lift.getStep(level);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getCurrentLevel() {
        return lift.getCurrentLevel();
    }

    @Override
    public void turnOff() {
        lift.getStep(Event.GO_OFF);

    }

    @Override
    public void turnOn() {
        lift.getStep(Event.GO_ON);

    }
    @Override
    public FSM getLift(){
        lift.setCurrentLevel(lift.getCurrentLevel());
        return lift;
    }
}
