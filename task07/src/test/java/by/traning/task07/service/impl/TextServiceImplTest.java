package by.traning.task07.service.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.bean.Composite;
import by.traning.task07.bean.Leaf;
import by.traning.task07.bean.Type;
import by.traning.task07.service.exception.ServiceException;
import by.traning.task07.service.factory.ServiceFactory;
import by.traning.task07.service.TextService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class TextServiceImplTest {
    private TextService textService;
    private Component component;
    private String text;

    @BeforeClass
    public void setUp() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        textService = serviceFactory.getTextService();
        TextService textService = serviceFactory.getTextService();
        text = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic " +
                "typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|" +
                "(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and " +
                "more recently with desktop publishing software like Aldus PageMaker including versions of Lorem " +
                "Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page " +
                "when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it" +
                " has a more-or-less normal distribution of letters, as opposed to using (Content here), content " +
                "here', making it look like readable English.";
        String path = "src/test/resources/textTest.txt";
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(text.getBytes());
            component = textService.createTree(path);
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "input_expectedForJoin")
    public Object[][] createCorrectDataForJoinTree(){
        return
                new Object[][]{
                        {text}
                };
    }

    @DataProvider(name = "input_expectedForCreate")
    public Object[][] createCorrectDataForCreateTree() throws ServiceException, IOException {
        String text = "Abc!\n" +
                "Abc, def.";
        String path = "src/test/resources/textTreeTest.txt";
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(path);
        fileOutputStream.write(text.getBytes());
        Component expected = textService.createTree(path);
        return
                new Object[][]{
                        {expected}
                };
    }

    @Test(description = "Positive script of the receive text collection")
    public void testFindComponent() {
        Map<Integer, Component> repository = textService.receiveTextCollection();
        Component sameComponent = repository.get(0);
        assertEquals(sameComponent, component);
    }

    @Test(description = "Positive script of the create tree",
            dataProvider = "input_expectedForCreate")
    public void testCreateTree(Component expected) {
        Composite text = new Composite(Type.TEXT);
        List<Component> paragraphs = text.getComponents();
        Composite paragraph1 = new Composite(Type.PARAGRAPH);
        paragraphs.add(paragraph1);
        List<Component> sentences1 = paragraph1.getComponents();
        Composite sentence1 = new Composite(Type.SENTENCE);
        sentences1.add(sentence1);
        List<Component> lexemes1 = sentence1.getComponents();
        Composite lexeme1 = new Composite(Type.LEXEME);
        lexemes1.add(lexeme1);
        Composite word1 = new Composite(Type.WORD);
        List<Component> wordsAndMarks = lexeme1.getComponents();
        wordsAndMarks.add(word1);
        Composite mark1 = new Composite(Type.MARK);
        wordsAndMarks.add(mark1);
        List<Component> charsOfWords1 = word1.getComponents();
        List<Component> charsOfMark1 = mark1.getComponents();
        Leaf character1 = new Leaf();
        character1.setSymbol('A');
        Leaf character2 = new Leaf();
        character2.setSymbol('b');
        Leaf character3 = new Leaf();
        character3.setSymbol('c');
        charsOfWords1.add(character1);
        charsOfWords1.add(character2);
        charsOfWords1.add(character3);
        Leaf character4 = new Leaf();
        character4.setSymbol('!');
        charsOfMark1.add(character4);
        Composite paragraph2 = new Composite(Type.PARAGRAPH);
        paragraphs.add(paragraph2);
        List<Component> sentences2 = paragraph2.getComponents();
        Composite sentence2 = new Composite(Type.SENTENCE);
        sentences2.add(sentence2);
        List<Component> lexemes2 = sentence2.getComponents();
        Composite lexeme2 = new Composite(Type.LEXEME);
        lexemes2.add(lexeme2);
        Composite word2 = new Composite(Type.WORD);
        List<Component> wordsAndMarks2 = lexeme2.getComponents();
        wordsAndMarks2.add(word2);
        Composite mark2 = new Composite(Type.MARK);
        wordsAndMarks2.add(mark2);
        List<Component> charsOfWords2 = word2.getComponents();
        List<Component> charsOfMark2 = mark2.getComponents();
        Leaf character21 = new Leaf();
        character21.setSymbol('A');
        Leaf character22 = new Leaf();
        character22.setSymbol('b');
        Leaf character23 = new Leaf();
        character23.setSymbol('c');
        charsOfWords2.add(character21);
        charsOfWords2.add(character22);
        charsOfWords2.add(character23);
        Leaf character24 = new Leaf();
        character24.setSymbol(',');
        charsOfMark2.add(character24);
        Composite lexeme3 = new Composite(Type.LEXEME);
        lexemes2.add(lexeme3);
        Composite word3 = new Composite(Type.WORD);
        List<Component> wordsAndMarks3 = lexeme3.getComponents();
        wordsAndMarks3.add(word3);
        Composite mark3 = new Composite(Type.MARK);
        wordsAndMarks3.add(mark3);
        List<Component> charsOfWords3 = word3.getComponents();
        List<Component> charsOfMark3 = mark3.getComponents();
        Leaf character31 = new Leaf();
        character31.setSymbol('d');
        Leaf character32 = new Leaf();
        character32.setSymbol('e');
        Leaf character33 = new Leaf();
        character33.setSymbol('f');
        charsOfWords3.add(character31);
        charsOfWords3.add(character32);
        charsOfWords3.add(character33);
        Leaf character34 = new Leaf();
        character34.setSymbol('.');
        charsOfMark3.add(character34);
        assertEquals(text, expected);
    }
}