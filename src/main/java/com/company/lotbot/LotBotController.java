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
    List<Transaction> completedTransactionsLog = new ArrayList<>();

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

        Transaction testTrans = new Transaction(car);
//      testTrans.setCheckedInDate(LocalDateTime.now()); this line is consolidated into the line above because we
        //made a checkedInDate variable in the constructor in Transaction
        transactionLog.add(testTrans);

        lots.get(id).getSpaces()[index] = new Space(testTrans);
//        Transaction startTrans = Transaction.makeTransaction(car, LocalDateTime.now());
//        transactionLog.add(startTrans);
//
//        lots.get(id).getSpaces()[index];
//        Space[] temp = lots.get(id).getSpaces();
//        temp[index] = new Space();
//        temp[index].setTransaction(startTrans);
    }

    @CrossOrigin
    @RequestMapping(path = "/lots/{id}/{index}", method = RequestMethod.PUT)
    private double unparkCar(@PathVariable("id") int id, @PathVariable("index") int index) {
        double time = 0;
        Transaction currentTrans = lots.get(id).getSpaces()[index].getTransaction();
        currentTrans.setCheckedOutDate(LocalDateTime.now());
        time = DateHelper.getHoursBetweenDates(currentTrans.getCheckedInDate(), currentTrans.getCheckedOutDate());
        currentTrans.setPrice(time);
        //set checked out
        //calc price
        //set the price
        //set index back to null
        System.out.println(time);
        lots.get(id).getSpaces()[index] = null;
        return currentTrans.getPrice();

    }

    @CrossOrigin
    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    public List<Transaction> getCompletedTransactionsLogTransactionLog() {
        for (int i = 0; i < transactionLog.size(); i++) {
            if (transactionLog.get(i).getCheckedOutDate() != null) {
                completedTransactionsLog.add(transactionLog.get(i));
            }
        }
        return completedTransactionsLog;
    }
}


