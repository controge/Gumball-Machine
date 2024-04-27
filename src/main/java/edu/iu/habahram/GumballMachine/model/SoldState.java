package edu.iu.habahram.GumballMachine.model;

public class SoldState implements IState{
    IGumballMachine gumballMachine;
    public SoldState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        String message = "You already made a purchase";
        boolean succeeded = false;
        int count = gumballMachine.getCount();
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);
    }
    @Override
    public TransitionResult ejectQuarter() {
        String message = "Your quarter was already used";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public TransitionResult turnCrank() {
        String message = "Crank has already been turned";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult dispense() {
        boolean succeeded;
        String message;
        if(gumballMachine.getCount() == 0) {
            succeeded = false;
            message = "Error: out of gumballs";
            gumballMachine.changeTheStateTo(GumballMachineState.OUT_OF_GUMBALLS);
        }else {
            gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
            message = "Dispensing gumball";
            succeeded = true;
        }
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

