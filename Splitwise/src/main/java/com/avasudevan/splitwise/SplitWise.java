package com.avasudevan.splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitWise {

    List<User> userList;

    public static Map<Integer, User> userMap;

    public static User getUser(int id) {
        return userMap.getOrDefault(id, new User(0, ""));
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        userMap = new HashMap<>();
        userList.forEach(u -> userMap.put(u.getId(), u));
    }

    public void addExpense(User spentUser, double amountSpent, String desc, SPLIT_TYPE splitType,
        List<User> usersInvolved, Map<Integer,Double> exactAmountMap, Map<Integer, Double> percentMap) {
        new Expense().addExpense(spentUser, amountSpent, desc, splitType, usersInvolved, exactAmountMap, percentMap);
    }

    public void printBalance() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        userList.forEach(user -> {
            System.out.println("User Name: " + user.getName() + ", Total Expense: " + user.getTotalExpenses());
            for(Map.Entry entry: user.getOweMap().entrySet()) {
                    if ((double)entry.getValue() > 0) {
                        System.out.println( userMap.get( (Integer) entry.getKey()).getName() +" owes "+user.getName() +" : " + entry.getValue());
                    } else {
                        System.out.println( user.getName() +" owes "+ userMap.get( (Integer) entry.getKey()).getName() +" : " + entry.getValue());
                    }
            }
        });

        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }
}
