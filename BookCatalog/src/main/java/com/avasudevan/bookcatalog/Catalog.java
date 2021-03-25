package com.avasudevan.bookcatalog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Catalog {

    private final List<Book> books;

    private final Map<String, PriorityQueue<Book>> authorMap = new HashMap<>();

    private final Map<String, PriorityQueue<Book>> categoryMap = new HashMap<>();

    public Catalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        PriorityQueue<Book> authorBookQueue = authorMap.getOrDefault(book.getAuthor(), new PriorityQueue<>(Comparator.comparingLong(Book::getSoldCount).reversed()));
        authorBookQueue.add(book);
        authorMap.put(book.getAuthor(), authorBookQueue);

        PriorityQueue<Book> categoryBookQueue = categoryMap.getOrDefault(book.getCategory().name(), new PriorityQueue<>(Comparator.comparingLong(Book::getSoldCount).reversed()));
        categoryBookQueue.add(book);
        categoryMap.put(book.getCategory().name(), categoryBookQueue);
    }

    public List<Book> searchBook(String name) {
        List<Book> foundBooks = new ArrayList<>();
        books.forEach(book -> {
                if(book.getName().startsWith(name) || book.getAuthor().startsWith(name)) {
                    foundBooks.add(book);
                }
            }
        );

        return foundBooks;
    }

    public Optional<Stream<Book>> topSoldByAuthor(String name, int limit) {
        if(!authorMap.containsKey(name)) {
            System.out.println("Author Name not found");
            return Optional.empty();
        }
        return Optional.of(authorMap.get(name).stream().limit(limit));
    }

    public Optional<Stream<Book>> topSoldByCategory(CATEGORY category, int limit) {

        if(!categoryMap.containsKey(category.name())) {
            System.out.println("Category Name not found");
            return Optional.empty();
        }
        return Optional.of(categoryMap.get(category.name()).stream().limit(limit));
    }
}
