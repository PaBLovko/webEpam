package by.traning.task07.service.impl;

import by.traning.task07.bean.Type;
import by.traning.task07.service.factory.ServiceFactory;
import by.traning.task07.service.SplitService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SplitServiceImplTest {

    @DataProvider(name = "dP")
    public Iterator<Object[]> callDP() {
        Object[][] objects = {{"Abc.\nDef.\nGhi!", Arrays.asList("Abc.", "Def.", "Ghi!"), Type.PARAGRAPH},
                {"Abc. Def.", Arrays.asList("Abc.", "Def."), Type.SENTENCE},
                {"Abc, Def.", Arrays.asList("Abc,", "Def."), Type.LEXEME},
                {"Abc, Def.", Arrays.asList("Abc", "Def"), Type.WORD},
                {"Abc, Def.", Arrays.asList(",", "."), Type.MARK},
                {"Abc.", Arrays.asList("A", "b", "c", "."), Type.CHARACTER}
        };
        List<Object[]> list = List.of(objects);
        return list.iterator();
    }

    @Test(dataProvider = "dP")
    public void testSplit(String string, List<String> list, Type type) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> actual = new ArrayList<>(splitService.split(string, type));
        if (actual.get(0).equals("")) {
            actual.remove(0);
        }
        assertEquals(actual, list);
    }
}
