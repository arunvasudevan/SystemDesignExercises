package com.avasudevan.bookcatalog;

import org.junit.Before;
import org.junit.Test;

public class CatalogTest {

    Catalog catalog;

    @Before
    public void init() {
        catalog = new Catalog();
        Book book1 = new Book("7 Habits of Highly Effective People",
            "Stephen Covey",
            "Simon and Schuster",
            1990,
            CATEGORY.SELF_HELP,
            10.00,
            3000000);

        Book book4 = new Book("8th Habit",
            "Stephen Covey",
            "Simon and Schuster",
            1990,
            CATEGORY.SELF_HELP,
            10.00,
            100000);
        catalog.addBook(book1);
        catalog.addBook(book4);

        Book book2 = new Book("Clean Code",
            "Uncle Bob",
            "Prentice Hall",
            2009,
            CATEGORY.TECHNICAL,
            12.00,
            1000000);

        Book book3 = new Book("Cleaner Code",
            "Uncle Bob",
            "Prentice Hall",
            20012,
            CATEGORY.TECHNICAL,
            12.00,
            500000);
        catalog.addBook(book2);
        catalog.addBook(book3);
    }

    @Test
    public void testSearchBookByName() {
        System.out.println("Search Book in Catalog Test.....");
        catalog.searchBook("7 Habits").forEach(Book::printDetails);
    }

    @Test
    public void testSearchBookByAuthor() {
        System.out.println("Search Book in Catalog Test.....");
        catalog.searchBook("Uncle").forEach(Book::printDetails);
    }

    @Test
    public void testTopSoldByAuthor() {
        System.out.println("Top Sold Books by Author....");

        catalog.topSoldByAuthor("Stephen Covey", 2).ifPresent(bookStream -> {
            bookStream.forEach(Book::printDetails);
        });
    }

    @Test
    public void testTopSoldByCategory() {
        System.out.println("Top Sold Books by Category....");

        catalog.topSoldByCategory(CATEGORY.TECHNICAL, 1).ifPresent(bookStream -> {
            bookStream.forEach(Book::printDetails);
        });
    }
}
