package by.traning.task07.service.parser.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.bean.Composite;
import by.traning.task07.bean.Type;
import by.traning.task07.service.factory.ServiceFactory;
import by.traning.task07.service.SplitService;
import by.traning.task07.service.parser.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LexemeParser implements Handler {
    private final Logger logger = LogManager.getLogger();
    private Handler rootWord;
    private Handler rootMark;

    public LexemeParser(Handler rootWord) {
        this.rootWord = rootWord;
    }

    public LexemeParser(Handler rootWord, Handler rootMark) {
        this.rootWord = rootWord;
        this.rootMark = rootMark;
    }

    @Override
    public void handleSplit(Component component, String content) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> parsed = splitService.split(content, Type.LEXEME);
        for (String element : parsed) {
            Composite lexeme = new Composite(Type.LEXEME);
            ((Composite) component).add(lexeme);
            if (element.startsWith("'")) {
                if (rootMark != null) {
                    rootMark.handleSplit(lexeme, element);
                }
                if (rootWord != null) {
                    rootWord.handleSplit(lexeme, element);
                }
            } else {
                if (rootWord != null) {
                    rootWord.handleSplit(lexeme, element);
                }
                if (rootMark != null) {
                    rootMark.handleSplit(lexeme, element);
                }
            }
        }
    }

}
