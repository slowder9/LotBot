package com.company.lotbot;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {
    private int id;
    private static int ID_COUNTER;
    private Car car;
    private LocalDateTime checkedInDate;
    private LocalDateTime checkedOutDate;
    private double price;

    public Transaction() {
    }

    public Transaction(Car car) {
        this.car = car;
        this.checkedInDate = LocalDateTime.now();
    }

    public Car getCar() {
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getCheckedInDate() {
        return checkedInDate;
    }

    public void setCheckedInDate(LocalDateTime checkedInDate) {
        this.checkedInDate = checkedInDate;
    }

    public LocalDateTime getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(LocalDateTime checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "car=" + car +
                ", checkedInDate=" + checkedInDate +
                ", checkedOutDate=" + checkedOutDate +
                ", price=" + price +
                '}';
    }

    public static Transaction makeTransaction(Car car, LocalDateTime checkedInDate) {
        Transaction t = new Transaction();
        t.setCar(car);
        t.setCheckedInDate(checkedInDate);
        t.setId(ID_COUNTER);
        ID_COUNTER++;
        return t;
    }
}
