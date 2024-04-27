package edu.iu.habahram.GumballMachine.model;

public class HasQuarterState implements IState{
    IGumballMachine gumballMachine;
    public HasQuarterState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        String message = "You already inserted a quarter";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }
    @Override
    public TransitionResult ejectQuarter() {
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        String message = "Your quarter was ejected";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public TransitionResult turnCrank() {
        gumballMachine.changeTheStateTo(GumballMachineState.GUMBALL_SOLD);
        String message = "About to dispense gumball";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult dispense() {
        boolean succeeded = false;
        String message = "You must turn the crank";
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult refill(int count) {
        String message = "Machine is not sold out!";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public String getTheName() {
        return GumballMachineState.NO_QUARTER.name();
    }
}
