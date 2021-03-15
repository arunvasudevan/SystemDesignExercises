package com.avasdevan.texteditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TextEditor {

    List<String> contents;

    StringBuilder clipboard;

    Stack<List<String>> undoStack;

    Stack<List<String>> redoStack;

    public TextEditor(List<String> contents) {
        this.contents = new ArrayList<>(contents);
        this.clipboard = new StringBuilder();
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void insert(int lineNo, String text) {
        if (contents.size() < lineNo) {
            System.out.println("Cannot insert Text, provided LineNo does not exist.");
            return;
        }
        undoStack.push(new ArrayList<>(contents));
        contents.add(lineNo - 1, text);
    }

    public void delete(int lineNo) {
        if (contents.size() < lineNo) {
            System.out.println("Cannot delete Text, provided LineNo does not exist.");
            return;
        }
        undoStack.push(new ArrayList<>(contents));
        contents.remove(lineNo - 1);
    }

    public void delete(int n, int m) {
        if(m < n) {
            System.out.println("Second Parameter should be greater than the first parameter");
            return;
        }
        if(m > contents.size()) {
            System.out.println("Text Editor does not contain "+m + " lines");
            return;
        }
        undoStack.push(new ArrayList<>(contents));
        contents.subList(n - 1, m).clear();
    }

    public void copy(int n, int m) {
        if(m < n) {
            System.out.println("Second Parameter should be greater than the first parameter");
            return;
        }
        if(m > contents.size()) {
            System.out.println("Text Editor does not contain "+m + " lines");
            return;
        }
        clipboard = new StringBuilder();
        for(int i = n-1; i < m; i++) {
            clipboard.append(contents.get(i));
            if(i != m-1) {
                clipboard.append("\n");
            }
        }
    }

    public void paste(int n) {
        if(n > contents.size() + 1) {
            System.out.println("Text Editor does not contain "+n + " lines");
            return;
        }
        if(clipboard.length() == 0) {
            System.out.println("No contents in clipboard...");
        }
        undoStack.push(new ArrayList<>(contents));
        contents.add(n-1, clipboard.toString());
    }

    public void redo() {
        if(redoStack.size() == 0) {
            System.out.println("No command to redo....");
            return;
        }
        undoStack.push(new ArrayList<>(contents));
        contents = redoStack.pop();
    }

    public void undo() {
        if(undoStack.size() == 0) {
            System.out.println("Nothing to Undo");
            return;
        }
        redoStack.push(new ArrayList<>(contents));
        contents = undoStack.pop();
    }


    public void display() {
        if(contents.size() == 0) {
            return;
        }
        System.out.println("---------------------------------------------------------");
        for (String content : contents) {
            System.out.println(content);
        }
    }

    public void display(int n, int m) {
        if(m < n) {
            System.out.println("Second Parameter should be greater than the first parameter");
            return;
        }
        if(m > contents.size()) {
            System.out.println("Text Editor does not contain "+m + " lines");
            return;
        }
        System.out.println("---------------------------------------------------------");
        for (int i = n - 1; i < m - 1; i++) {
            System.out.println(contents.get(i));
        }
    }
}
