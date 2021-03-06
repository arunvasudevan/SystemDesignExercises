package com.avasudevan.splitwise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SplitWiseTest {

    SplitWise splitwise;
    List<User> userList;
    User user1, user2, user3, user4;

    @Before
    public void setup() {
        splitwise = new SplitWise();

        user1 = new User(1, "Marlin");
        user2 = new User(2, "Nemo");
        user3 = new User(3, "Dory");
        user4 = new User(4, "Turtles");

        userList = new ArrayList<>();
        userList = Arrays.asList(user1, user2, user3, user4);
    }

    @Test
    public void testSplitWiseForEqualSplit() {
        splitwise.setUserList(userList);
        splitwise.addExpense(user1, 400.0, "Tickets to Great Barrier reef", SPLIT_TYPE.EQUALLY, userList, null, null);
        splitwise.addExpense(user3, 1200.0, "Stay at Shark Town", SPLIT_TYPE.EQUALLY, userList, null, null);
        System.out.println("Equal Split:");
        splitwise.printBalance();
    }

    @Test
    public void testSplitWiseForExactSplit() {
        splitwise.setUserList(userList);
        Map<Integer, Double> exactAmountMap = new HashMap<>();
        exactAmountMap.put(1, (double) 200);
        exactAmountMap.put(2, (double) 100);
        exactAmountMap.put(3, (double) 300);
        exactAmountMap.put(4, (double) 50);
        splitwise.addExpense(user1, 650.0, "Tickets to Great Barrier reef", SPLIT_TYPE.EXACT, userList, exactAmountMap, null);
        System.out.println("Exact Split:");
        splitwise.printBalance();
    }

    @Test
    public void testSplitWiseForPercentSplit() {
        splitwise.setUserList(userList);
        Map<Integer, Double> percentMap = new HashMap<>();
        percentMap.put(1, (double) 25);
        percentMap.put(2, (double) 40);
        percentMap.put(3, (double) 25);
        percentMap.put(4, (double) 20);
        splitwise.addExpense(user1, 1000, "Tickets to Great Barrier reef", SPLIT_TYPE.PERCENTAGE, userList, null, percentMap);
        System.out.println("Percent Split:");
        splitwise.printBalance();
    }
}
