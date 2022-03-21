package com.gar.machinecoding;

import com.gar.machinecoding.model.expense.ExpenseType;
import com.gar.machinecoding.model.split.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addUser(new User("u1", "Uname1", "bulgogi@gamil.com", "1234567890"));
        expenseManager.addUser(new User("u2", "Uname2", "balsamic@gamil.com", "2340945687"));
        expenseManager.addUser(new User("u3", "Uname3", "sushi@gamil.com", "2134098345"));
        expenseManager.addUser(new User("u4", "Uname4", "friedchicken@gamil.com", "9678453666"));

        Scanner sc = new Scanner(System.in);
        while(true) {
            String command = sc.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "SHOW" :
                    if(commands.length == 1) {
                        expenseManager.showBalances();
                    } else {
                        expenseManager.showBalance(commands[1]);
                    }
                    break;
                case "EXPENSE" :
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUser = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUser];
                    List<Split> splits = new ArrayList<>();
                    switch (expenseType) {
                        case "EQUAL":
                            for(int i = 0; i < noOfUser; i++) {
                                splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
                            break;
                        case "EXACT":
                            for(int i = 0 ; i < noOfUser; i++) {
                                splits.add(new ExactSplit(expenseManager.userMap.get(commands[4 + i]),
                                        Double.parseDouble(commands[5 + i + noOfUser])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
                            break;
                        case "PERCENT" :
                            for (int i = 0; i<noOfUser; i++) {
                                splits.add(new PercentSplit(expenseManager.userMap.get(commands[4+i]),
                                        Double.parseDouble(commands[5+i+noOfUser])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
                    }
            }
        }
    }
}
