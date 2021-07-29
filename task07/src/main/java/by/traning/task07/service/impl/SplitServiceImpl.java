package by.traning.task07.service.impl;

import by.traning.task07.bean.Type;
import by.traning.task07.service.SplitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class SplitServiceImpl implements SplitService {
    private EnumMap<Type, String> repository = new EnumMap<>(Type.class);
    private final Logger logger = LogManager.getLogger();

    public SplitServiceImpl() {
        repository.put(Type.PARAGRAPH, "\n");
        repository.put(Type.SENTENCE, "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s");
        repository.put(Type.LEXEME, "\\s");
        repository.put(Type.WORD, "[.?!, ']+");
        repository.put(Type.MARK, "[^,.!?']+");
        repository.put(Type.CHARACTER, "");
    }

    @Override
    public List<String> split(String element, Type dest) {
        List<String> result = Arrays.asList(element.split(repository.get(dest)));
        return result;
    }
}
