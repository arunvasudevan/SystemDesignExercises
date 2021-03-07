package com.avasudevan.splitwise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Expense {

    static int id = 1;
    private String desc;

    public void addExpense(User spentUser, double amountSpent, String desc, SPLIT_TYPE splittype, List<User> userList,
        List<Double> exactAmountList, List<Double> percentList) {
        id = getUniqueId();
        this.desc = desc;

        if (splittype.equals(SPLIT_TYPE.EQUALLY)) {
            calculateEqual(spentUser, amountSpent, userList);
        }
        if (splittype.equals(SPLIT_TYPE.EXACT)) {
            calculateExact(spentUser, amountSpent, exactAmountList);
        }
        if (splittype.equals(SPLIT_TYPE.PERCENTAGE)) {
            calculatePercentage(spentUser, amountSpent, percentList);
        }
    }

    private void calculateEqual(User spentUser, double amountSpent, List<User> userList) {
        spentUser.setTotalExpenses(spentUser.getTotalExpenses() + amountSpent);
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);

            double amountToSplit = Math.floor((100.0 * amountSpent) / (userList.size() - i)) / 100.0;
            user.setTotalExpenses(user.getTotalExpenses() - amountToSplit);
            if (user.getId() != spentUser.getId()) {
                spentUser.getOweMap().put(user.getId(), spentUser.getOweMap().getOrDefault(user.getId(), (double) 0) + amountToSplit);
                user.getOweMap().put(spentUser.getId(), user.getOweMap().getOrDefault(spentUser.getId(), (double) 0) - amountToSplit);
            }
            amountSpent -= amountToSplit;
        }
    }

    private void calculatePercentage(User spentUser, double amountSpent, List<Double> percentList) {

        if(percentList.stream().mapToDouble(t -> t).sum() != 100) {
            System.out.println("Sum of all the percentages does not equal 100. Could not proceed, kindly fix the error and re-process!");
            return;
        }

        for(int i = 1; i <= percentList.size(); i++) {
            User currentUser = SplitWise.getUser(i);

            double amountPerUser = (percentList.get(i - 1) * amountSpent) / 100.0;
            updateUserExpenses(spentUser, currentUser, amountPerUser);
        }
    }

    private void calculateExact(User spentUser, double amountSpent, List<Double> exactAmountList) {

        if(exactAmountList.stream().mapToDouble(t -> t).sum() != amountSpent) {
            System.out.println("Exact amount does not equal the amount spent. Could not proceed, kindly fix the error and re-process!");
            return;
        }

        for(int i = 1; i <= exactAmountList.size(); i++) {
            User currentUser = SplitWise.getUser(i);
            double userExactAmount = exactAmountList.get(i - 1);
            updateUserExpenses(spentUser, currentUser, userExactAmount);
        }
    }

    private void updateUserExpenses(User spentUser, User currentUser, double userExactAmount) {
        if(currentUser.getId() != spentUser.getId()) {
            currentUser.setTotalExpenses(currentUser.getTotalExpenses() - userExactAmount);
            spentUser.getOweMap().put(currentUser.getId(), spentUser.getOweMap().getOrDefault(currentUser.getId(), (double) 0) + userExactAmount);
            currentUser.getOweMap().put(spentUser.getId(), currentUser.getOweMap().getOrDefault(spentUser.getId(), (double) 0) - userExactAmount);
        }
    }

    private int getUniqueId() {
        return id++;
    }
}
