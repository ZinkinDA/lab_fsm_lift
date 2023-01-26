package com.example.project_lift_fsm;

import com.example.project_lift_fsm.model.Enum.Event;
import com.example.project_lift_fsm.model.Exception.MyException;
import com.example.project_lift_fsm.model.FSM;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectLiftFsmApplication {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(
                ProjectLiftFsmApplication.class, args

        );
//        FSM fsm = FSM.getInstance();//Получить лифт
//        fsm.getStep(5);//Отправить его на 5 этаж
//        fsm.getStep(Event.GO_OFF);
    }

}
