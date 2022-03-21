package com.gar.machinecoding.model.expense;

import com.gar.machinecoding.model.split.EqualSplit;
import com.gar.machinecoding.model.split.Split;
import com.gar.machinecoding.model.split.User;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMd) {
        super(amount, paidBy, splits, expenseMd);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }
        return true;
    }
}
