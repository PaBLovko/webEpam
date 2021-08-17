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
    private final Logger logger = LogManager.getLogger(LexemeParser.class);

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED = "The method is invoked";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String THE_METHOD_WORKED_CORRECTLY = "The method worked correctly";

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
        logger.debug(METHOD_IS_INVOKED);
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
        logger.info(THE_METHOD_WORKED_CORRECTLY);
    }

}
