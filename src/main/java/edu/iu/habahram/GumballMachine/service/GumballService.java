package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.*;
import edu.iu.habahram.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GumballService implements IGumballService{

    IGumballRepository gumballRepository;

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        return transitionSwitch(id, "insert");
    }

    @Override
    public TransitionResult ejectQuarter(String id) throws IOException {
        return transitionSwitch(id, "eject");
    }

    @Override
    public TransitionResult turnCrank(String id) throws IOException {
        return transitionSwitch(id, "turn");
    }


    public TransitionResult transitionSwitch(String id, String method) throws IOException {
        TransitionResult result = new TransitionResult(false, "", "", 0);
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine2(record.getId(), record.getState(), record.getCount());
        result = switch (method) {
            case "insert" -> machine.insertQuarter();
            case "eject" -> machine.ejectQuarter();
            case "turn" -> machine.turnCrank();
            default -> result;
        };
        if(result.succeeded()){
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }
}
