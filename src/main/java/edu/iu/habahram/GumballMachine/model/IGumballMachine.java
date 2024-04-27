package edu.iu.habahram.GumballMachine.model;

public interface IGumballMachine {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    TransitionResult refill(int count);
    void changeTheStateTo(GumballMachineState name);
    Integer getCount();
    void setCount(int count);
    String getTheStateName();

    void releaseBall();
}
