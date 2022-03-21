package com.gar.machinecoding;

import com.gar.machinecoding.model.expense.*;
import com.gar.machinecoding.model.split.PercentSplit;
import com.gar.machinecoding.model.split.Split;
import com.gar.machinecoding.model.split.User;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {

        switch (expenseType) {
            case EXACT:
                return new ExactExpense(amount, paidBy, splits, expenseMetadata);
            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                return new PercentExpense(amount, paidBy, splits, expenseMetadata);
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = ((double) Math.round(amount*100/totalSplits))/100.0;
                for(Split split : splits) {
                    split.setAmount(splitAmount);
                }
                return new EqualExpense(amount, paidBy, splits, expenseMetadata);
            default :
                return null;
        }
    }
}
