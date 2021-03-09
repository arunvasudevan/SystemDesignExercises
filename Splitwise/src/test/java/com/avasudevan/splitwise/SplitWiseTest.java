package com.avasudevan.splitwise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Double> exactAmountList = Arrays.asList(200.0, 100.0, 300.0, 50.0);
        splitwise.addExpense(user1, 650.0, "Tickets to Great Barrier reef", SPLIT_TYPE.EXACT, userList, exactAmountList, null);
        System.out.println("Exact Split:");
        splitwise.printBalance();
    }

    @Test
    public void testSplitWiseForPercentSplit() {
        splitwise.setUserList(userList);
        List<Double> percentList = Arrays.asList(25.0, 30.0, 25.0, 20.0);
        splitwise.addExpense(user1, 1000, "Tickets to Great Barrier reef", SPLIT_TYPE.PERCENTAGE, userList, null, percentList);
        System.out.println("Percent Split:");
        splitwise.printBalance();
    }

    @Test
    public void testSplitWiseSimplify() {
        splitwise.setUserList(userList);
        splitwise.addExpense(user1, 400.0, "Tickets to Great Barrier reef", SPLIT_TYPE.EQUALLY, userList, null, null);
        splitwise.addExpense(user3, 1200.0, "Stay at Shark Town", SPLIT_TYPE.EQUALLY, userList, null, null);
        splitwise.simplifyExpenses();
        System.out.println("Simplify Test:");
        splitwise.printBalance();
    }
}
