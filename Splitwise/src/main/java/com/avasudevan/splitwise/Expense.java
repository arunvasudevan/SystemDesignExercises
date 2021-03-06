package com.avasudevan.splitwise;

import java.util.List;
import java.util.Map;

public class Expense {

    static int id = 1;
    private String desc;

    public void addExpense(User spentUser, double amountSpent, String desc, SPLIT_TYPE splittype, List<User> userList,
        Map<Integer, Double> exactAmountMap, Map<Integer, Double> percentMap) {
        id = id++;
        this.desc = desc;

        if (splittype.equals(SPLIT_TYPE.EQUALLY)) {
            calculateEqual(spentUser, amountSpent, userList);
        }
        if (splittype.equals(SPLIT_TYPE.EXACT)) {
            calculateExact(spentUser, exactAmountMap);
        }
        if (splittype.equals(SPLIT_TYPE.PERCENTAGE)) {
            calculatePercentage(spentUser, amountSpent, percentMap);
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

    private void calculatePercentage(User spentUser, double amountSpent, Map<Integer, Double> percentMap) {

        for(Map.Entry<Integer, Double> entry: percentMap.entrySet()) {
            User currentUser = SplitWise.getUser(entry.getKey());

            double amountPerUser = (entry.getValue() * amountSpent) / 100.0;
            if(currentUser.getId() != spentUser.getId()) {
                currentUser.setTotalExpenses(currentUser.getTotalExpenses() - amountPerUser);
                spentUser.getOweMap().put(currentUser.getId(), spentUser.getOweMap().getOrDefault(currentUser.getId(), (double) 0) + amountPerUser);
                currentUser.getOweMap().put(spentUser.getId(), currentUser.getOweMap().getOrDefault(spentUser.getId(), (double) 0) - amountPerUser);
            }
        }
    }

    private void calculateExact(User spentUser, Map<Integer, Double> exactAmountMap) {

        for(Map.Entry<Integer, Double> entry: exactAmountMap.entrySet()) {
            User currentUser = SplitWise.getUser( entry.getKey());
            if(currentUser.getId() != spentUser.getId()) {
                currentUser.setTotalExpenses(currentUser.getTotalExpenses() - entry.getValue());
                spentUser.getOweMap().put(currentUser.getId(), spentUser.getOweMap().getOrDefault(currentUser.getId(), (double) 0) + entry.getValue());
                currentUser.getOweMap().put(spentUser.getId(), currentUser.getOweMap().getOrDefault(spentUser.getId(), (double) 0) - entry.getValue());
            }
        }
    }
}
