package com.gar.machinecoding.model.expense;

import com.gar.machinecoding.model.split.Split;
import com.gar.machinecoding.model.split.User;

import java.util.List;

public abstract class Expense {
    private String id;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetadata expenseMd;

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMd) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseMd = expenseMd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public ExpenseMetadata getExpenseMd() {
        return expenseMd;
    }

    public void setExpenseMd(ExpenseMetadata expenseMd) {
        this.expenseMd = expenseMd;
    }

    public abstract boolean validate();
}
