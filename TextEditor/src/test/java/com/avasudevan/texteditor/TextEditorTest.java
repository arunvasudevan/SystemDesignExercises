package com.avasudevan.texteditor;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.avasdevan.texteditor.TextEditor;

public class TextEditorTest {

    TextEditor textEditor;

    @Before
    public void init() {
        textEditor = new TextEditor(Arrays.asList("This is the first line", "Starting to write more"));
    }

    @Test
    public void testInsert() {
        System.out.println("Insert Test............");
        textEditor.insert(1, "Inserting at the first line");
        textEditor.display();
        textEditor.display(1,2);
    }

    @Test
    public void testDelete() {
        System.out.println("Delete Test......");
        textEditor.delete(1);
        textEditor.display();
    }

    @Test
    public void testDeleteRange() {
        System.out.println("Delete Range Test......");
        textEditor.delete(1, 1);
        textEditor.display();
    }

    @Test
    public void testCopyAndPaste() {
        System.out.println("Copy and Paste Test.....");
        textEditor.copy(1,2);
        textEditor.paste(3);
        textEditor.display();
    }

    @Test
    public void testRedo() {
        System.out.println("Redo Test.....");
        System.out.println("Copying 2nd line to 3rd.....");
        textEditor.copy(2,2);
        textEditor.paste(3);
        System.out.println("Before Undo....");
        textEditor.display();
        textEditor.undo();
        System.out.println("After Undo....");
        textEditor.display();
        textEditor.redo();
        System.out.println("After Redo....");
        textEditor.display();
    }

    @Test
    public void testUndoPaste() {
        System.out.println("Undo Test for Paste.....");
        System.out.println("Initial Contents....");
        textEditor.display();
        textEditor.copy(2,2);
        textEditor.paste(3);
        System.out.println("After Paste (Before Undo).....");
        textEditor.display();
        System.out.println("After Undo.....");
        textEditor.undo();
        textEditor.display();
    }

    @Test
    public void testUndoDelete() {
        System.out.println("Undo Test for Delete.....");
        System.out.println("Initial Contents....");
        textEditor.display();
        textEditor.delete(1, 1);
        System.out.println("After Delete (Before Undo).....");
        textEditor.display();
        System.out.println("After Undo.....");
        textEditor.undo();
        textEditor.display();
    }
}
