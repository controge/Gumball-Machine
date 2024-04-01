package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;

    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "You can't insert another quarter";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = "You inserted a quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        boolean succeeded = false;
        String message = "";
        if(state.equalsIgnoreCase(HAS_QUARTER)){
            state = NO_QUARTER;
            message = "Ejecting quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(NO_QUARTER)){
            message = "There is no quarter to eject.";
        }
        else if(state.equalsIgnoreCase(SOLD_OUT)){
            message = "Machine is sold out!";
        }else if(state.equalsIgnoreCase(SOLD)){
            message = "Cannot eject your quarter: you already used it";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult turnCrank() {
        boolean succeeded = false;
        String message = "";
        if(state.equalsIgnoreCase(HAS_QUARTER)){
            state = SOLD;
            message = "About to dispense gumball.";
            succeeded = true;
        } else if (state.equalsIgnoreCase(NO_QUARTER)){
            message = "Cannot buy gumball with no quarter.";
        }
        else if(state.equalsIgnoreCase(SOLD_OUT)){
            message = "Machine is sold out!";
        }else if(state.equalsIgnoreCase(SOLD)){
            message = "Dispensing you gumball...";
            state = NO_QUARTER;
            succeeded = true;
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {

    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public void releaseBall() {

    }


}
