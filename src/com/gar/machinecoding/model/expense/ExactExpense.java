package com.gar.machinecoding.model.expense;

import com.gar.machinecoding.model.split.ExactSplit;
import com.gar.machinecoding.model.split.Split;
import com.gar.machinecoding.model.split.User;

import java.util.List;

public class ExactExpense extends Expense {

    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMd) {
        super(amount, paidBy, splits, expenseMd);
    }

    @Override
    public boolean validate() {
        for (Split split: getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }
        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount += exactSplit.getAmount();
        }
        if (totalAmount != sumSplitAmount) {
            return false;
        }

        return true;
    }
}
