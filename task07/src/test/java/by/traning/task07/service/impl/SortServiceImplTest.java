package by.traning.task07.service.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.bean.Composite;
import by.traning.task07.bean.Type;
import by.traning.task07.service.exception.ServiceException;
import by.traning.task07.service.factory.ServiceFactory;
import by.traning.task07.service.SortService;
import by.traning.task07.service.TextService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SortServiceImplTest {
    private TextService textService;
    private SortService sortService;
    private String path;

    @BeforeClass
    public void setUp() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        sortService = serviceFactory.getSortService();
        textService = serviceFactory.getTextService();
        String text = "One.\nTwo. Three. Four.\nFive. Six.";
        String path = "src/test/resources/textTest.txt";
        this.path = path;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSortWords() {
        String sentence = "It is a established fact that a reader will be of a page when looking at its layout.";
        List<String> actual = sortService.sortWords(sentence);
        List<String> expected = Arrays.asList("a", "a", "a", "It", "is", "be", "of", "at", "its", "fact",
                "that", "will", "page", "when", "reader", "layout", "looking", "established");
        assertEquals(actual, expected);
    }

    @Test
    public void testSortLexemes() {
        String symbol = "e";
        List<Component> components = new ArrayList<>();
        List<Component> actual = new ArrayList<>();
        try {
            Component component = textService.createTree(path);
            components = ((Composite) component).getByType(Type.LEXEME);
            actual = sortService.sortLexemes(component, symbol);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        List<Component> expected = Arrays.asList(components.get(2), components.get(4), components.get(0),
                components.get(3), components.get(5), components.get(1));
        assertEquals(actual, expected);
    }

    @Test
    public void testSortParagraphs() {
        List<Component> components = new ArrayList<>();
        List<Component> actual = new ArrayList<>();
        try {
            Component component = textService.createTree(path);
            components = ((Composite) component).getByType(Type.PARAGRAPH);
            actual = sortService.sortParagraphs(component);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        List<Component> expected = Arrays.asList(components.get(0), components.get(2), components.get(1));
        assertEquals(actual, expected);
    }
}
