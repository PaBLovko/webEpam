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

public class SentenceParser implements Handler {
    private final Logger logger = LogManager.getLogger(SentenceParser.class);

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED = "The method is invoked";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String THE_METHOD_WORKED_CORRECTLY = "The method worked correctly";

    private Handler root;

    public SentenceParser(Handler root) {
        this.root = root;
    }

    @Override
    public void handleSplit(Component component, String content) {
        logger.debug(METHOD_IS_INVOKED);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> parsed = splitService.split(content, Type.SENTENCE);
        for (String element : parsed) {
            Composite sentence = new Composite(Type.SENTENCE);
            ((Composite) component).add(sentence);
            if (root != null) {
                root.handleSplit(sentence, element);
            }
        }
        logger.info(THE_METHOD_WORKED_CORRECTLY);
    }
}
