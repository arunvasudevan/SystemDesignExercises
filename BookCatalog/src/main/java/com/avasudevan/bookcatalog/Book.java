package com.avasudevan.bookcatalog;

public class Book {

    private final int id;

    private final String name;

    private final String author;

    private final String publisher;

    private final int year;

    private final CATEGORY category;

    private final double price;

    private final long soldCount;

    static int bookId = 1;

    public Book(String name, String author, String publisher, int year, CATEGORY category, double price, long soldCount) {
        this.id = bookId++;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.category = category;
        this.price = price;
        this.soldCount = soldCount;
    }

    public void printDetails() {
        System.out.println(this.name + ", " + this.author + ", " + this.category + ", " + this.publisher + ", " + this.year + ", " + this.price + ", " + this.soldCount);
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public long getSoldCount() {
        return soldCount;
    }

    public CATEGORY getCategory() {
        return category;
    }
}
