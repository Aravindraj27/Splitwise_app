package com.gar.machinecoding;

import com.gar.machinecoding.model.expense.Expense;
import com.gar.machinecoding.model.expense.ExpenseMetadata;
import com.gar.machinecoding.model.expense.ExpenseType;
import com.gar.machinecoding.model.split.Split;
import com.gar.machinecoding.model.split.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<String, Double>());
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits, expenseMetadata);
        expenses.add(expense);
        for(Split split : expense.getSplits()) {
            String paidTo = split.getUser().getId();
            Map<String, Double> balances = balanceSheet.get(paidBy);
            if(!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.00);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.00);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }

    public void showBalances() {
        boolean isEmpty = true;
        for(Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for(Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if(userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }
        if(isEmpty) {
            System.out.println("No Balances.");
        }
    }

    public void showBalance(String userId) {
        boolean isEmpty = true;
        for(Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if(userBalance.getValue() >0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }
        if(isEmpty) {
            System.out.println("No Balance");
        }
    }

    private void printBalance(String userId1, String userId2, Double amount) {
        String uName1 = userMap.get(userId1).getName();
        String uName2 = userMap.get(userId2).getName();
        if(amount <0) {
            System.out.println(uName1 + " owes " + uName2 + " : " + Math.abs(amount));
        } else if(amount >0) {
            System.out.println(uName2 + " owes " + uName1 + " : " + Math.abs(amount));
        }
    }
}
