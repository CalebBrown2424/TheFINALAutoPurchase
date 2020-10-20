package com.example.therealautopurchase;

public class Auto {

    static final double TAX = 0.07;


    private double carPrice;
    private double downPayment;
    private double APRamount;
    private int loanTerm;

    public void setPrice(double price){
        carPrice = price;
    }

    public double getPrice(){
        return carPrice;
    }

    public void setDownPayment(double down){
        downPayment = down;
    }

    public double getDownPayment(){
        return downPayment;
    }
    public void setAPRamount(double apr)
    {
        APRamount = apr;
    }
    public double getAPRamount()
    {
        return APRamount;
    }

    public void setLoanTerm(String term){
        if(term.contains("2"))
            loanTerm = 2;
        else if(term.contains("3"))
            loanTerm = 3;
        else if(term.contains("4"))
            loanTerm = 4;
        else if(term.contains("5"))
            loanTerm = 5;
        else if(term.contains("6"))
            loanTerm = 6;
        else
            loanTerm = 7;
    }

    public int getLoanTerm(){
        return loanTerm;
    }

    public double taxAmount(){
        return carPrice * TAX;
    }

    public double totalCost(){
        return carPrice + taxAmount();
    }

    public double borrowedAmount(){
        return totalCost() - downPayment;
    }

//calculating for interest compounded monthly as opposed to simple interest
    public double interestAmount(){
        return Math.pow(borrowedAmount()*(1 + APRamount/12), APRamount*12);
    }

    public double monthlyPayment(){
        return borrowedAmount() / (loanTerm * 12);
    }

}
