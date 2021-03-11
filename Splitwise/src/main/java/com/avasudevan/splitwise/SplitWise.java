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
        List<User> usersInvolved, List<Double> exactAmountList, List<Double> percentList) {
        new Expense().addExpense(spentUser, amountSpent, desc, splitType, usersInvolved, exactAmountList, percentList);
    }

    public void printBalance() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        userList.forEach(user -> {
            System.out.println("User Name: " + user.getName() + ", Total Expense: " + user.getTotalExpenses());
            for(Map.Entry<Integer, Double> entry: user.getOweMap().entrySet()) {
                    if (entry.getValue() > 0) {
                        System.out.println( userMap.get(entry.getKey()).getName() +" --> "+user.getName() +" : " + Math.abs(entry.getValue()));
                    } else {
                        System.out.println( user.getName() +" --> "+ userMap.get(entry.getKey()).getName() +" : " + Math.abs(entry.getValue()));
                    }
            }
        });

        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }


    public void simplifyExpenses() {
        // TODO: Reduce the Complexity of this Algorithm from O(n ^3)
        double[][] adjMatrix = new double[userList.size()+1][userList.size()+1];

        userList.forEach( u -> {
                if(u.getOweMap().size() > 0) {
                    for(Map.Entry<Integer, Double> entry: u.getOweMap().entrySet()) {
                        adjMatrix[entry.getKey()][u.getId()] = entry.getValue();
                    }
                }
            }
        );

        for(int i = 1; i <= userList.size(); i++) {
            for(int j = 1; j <= userList.size(); j++) {
                if(adjMatrix[i][j] > 0) {
                    for(int k = 1; k <= userList.size(); k++) {
                        if(adjMatrix[k][i] > 0 && adjMatrix[k][j] > 0) {
                            if(adjMatrix[i][j] >= adjMatrix[k][i]) {
                                adjMatrix[i][j] -= adjMatrix[k][i];
                                adjMatrix[k][j] += adjMatrix[k][i];
                                adjMatrix[k][i] = 0;
                            } else {
                                adjMatrix[k][i] -= adjMatrix[i][j];
                                adjMatrix[k][j] += adjMatrix[i][j];
                                adjMatrix[i][j] = 0;
                            }

                            updateUserOweMap(adjMatrix, i, j);
                            updateUserOweMap(adjMatrix, k, i);
                            updateUserOweMap(adjMatrix, k, j);
                        }
                    }
                }
            }
        }
    }

    private void updateUserOweMap(double[][] adjMatrix, int i, int j) {
        User userI = userMap.get(i);
        User userJ = userMap.get(j);
        if(adjMatrix[i][j] > 0) {
            userI.getOweMap().put(j, adjMatrix[i][j] * -1);
            userJ.getOweMap().put(i, adjMatrix[i][j]);
        } else {
            userI.getOweMap().remove(j);
            userJ.getOweMap().remove(i);
        }
    }
}
