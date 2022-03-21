package com.gar.machinecoding.model.expense;

import com.gar.machinecoding.model.split.PercentSplit;
import com.gar.machinecoding.model.split.Split;
import com.gar.machinecoding.model.split.User;

import java.util.List;

public class PercentExpense extends Expense {

    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMd) {
        super(amount, paidBy, splits, expenseMd);
    }

    @Override
    public boolean validate() {
        for(Split split : getSplits()) {
            if(!(split instanceof PercentSplit)) {
                return false;
            }
        }

        double totalPercent = 100;
        double sumSplitPercent = 0;
        for(Split split : getSplits()) {
            PercentSplit percentSplit = (PercentSplit) split;
            sumSplitPercent += percentSplit.getPercent();
        }

        if(sumSplitPercent != totalPercent) {
            return false;
        }
        return true;
    }
}
