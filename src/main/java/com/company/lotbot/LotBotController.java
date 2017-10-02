package com.company.lotbot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@RestController
public class LotBotController {

    List<Lot> lots = new ArrayList<>();
    List<Transaction> transactionLog = new ArrayList<>();

    /**
     * The @PostConstruct method will cause whichever
     * method it's annotating to run after the controller
     * is created by Spring.
     * <p>
     * In this case, let's use it to give a value to
     * our list of "lots"
     */
    @PostConstruct
    public void postConstruct() {
        // we have to create lots of lots here
        // if you want fewer lots than 20
        // that's okay too
        for (int i = 0; i < 5; i++) {
            lots.add(Lot.createLot());
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/lots", method = RequestMethod.GET)
    public List<Lot> getLots() {
        return lots;
    }

    @CrossOrigin
    @RequestMapping(path = "/lots/{id}", method = RequestMethod.GET)
    public Lot getSpaces(@PathVariable("id") int id) {
        return lots.get(id);
    }

    @CrossOrigin
    @RequestMapping(path = "/lots/{id}/{index}", method = RequestMethod.POST)
    public void parkCar(@RequestBody Car car, @PathVariable("id") int id, @PathVariable("index") int index) {
        // create new Transaction object
        // set the car and checkedIn field of that object
        // add that transaction to a transactions List
        // set this transaction to the line below
        Transaction startTrans = Transaction.makeTransaction(car, LocalDateTime.now());
        transactionLog.add(startTrans);

//        lots.get(id).getSpaces()[index];
        Space[] temp = lots.get(id).getSpaces();
        temp[index] = new Space();
        temp[index].setTransaction(startTrans);
    }
}

//    @CrossOrigin
//    @RequestMapping(path = "/lots/{id}/{space}", method = RequestMethod.PUT)
//    private void unparkCar(@RequestBody Car car, @PathVariable("in") int id, @PathVariable("space") int space) {
//        double time = 0;
//
//    }
