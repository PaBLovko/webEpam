package by.traning.task07.service.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.bean.Composite;
import by.traning.task07.bean.Type;
import by.traning.task07.service.exception.ServiceException;
import by.traning.task07.service.factory.ServiceFactory;
import by.traning.task07.service.SortService;
import by.traning.task07.service.TextService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SortServiceImplTest {
    private SortService sortService;
    private Component component;

    @BeforeClass
    public void setUp() throws IOException, ServiceException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        sortService = serviceFactory.getSortService();
        TextService textService = serviceFactory.getTextService();
        String text = "One.\n" +
                "Two. Three. Four.\n" +
                "Five. Six.";
        String path = "src/test/resources/textSortTest.txt";
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(path);
        fileOutputStream.write(text.getBytes());
        component = textService.createTree(path);
    }

    @DataProvider(name = "expected_sortWords")
    public Object[][] createCorrectDataForSortWords(){
        List<Component> components = ((Composite) component).getByType(Type.WORD);
        return
                new Object[][]{
                        {Arrays.asList(components.get(0), components.get(1), components.get(5),
                                components.get(3),components.get(4), components.get(2))}
                };
    }

    @DataProvider(name = "expected_sortParagraphs")
    public Object[][] createCorrectDataForSortParagraphs(){
        List<Component> components = ((Composite) component).getByType(Type.PARAGRAPH);
        return
                new Object[][]{
                        {Arrays.asList(components.get(0), components.get(2), components.get(1))}
                };
    }

    @DataProvider(name = "expected_sortLexemes")
    public Object[][] createCorrectDataForSortLexemes(){
        List<Component> components = ((Composite) component).getByType(Type.LEXEME);
        return
                new Object[][]{
                        {Arrays.asList(components.get(2), components.get(4), components.get(0),
                                components.get(3), components.get(5), components.get(1))}
                };
    }

    @Test(description = "Positive script of the sort words",
            dataProvider = "expected_sortWords")
    public void testSortWords(List<Component> expected) {
        List<Component> actual = sortService.sortWords(component);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the sort lexemes",
            dataProvider = "expected_sortLexemes")
    public void testSortLexemes(List<Component> expected) throws ServiceException {
        List<Component> actual = sortService.sortLexemes(component, "e");
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the sort paragraphs",
            dataProvider = "expected_sortParagraphs")
    public void testSortParagraphs(List<Component> expected) {
        List<Component> actual = sortService.sortParagraphs(component);
        assertEquals(actual, expected);
    }
}
