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

public class ParagraphParser implements Handler {
    private final Logger logger = LogManager.getLogger();
    private Handler root;

    public ParagraphParser(Handler root) {
        this.root = root;
    }

    @Override
    public void handleSplit(Component component, String content) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SplitService splitService = serviceFactory.getSplitService();
        List<String> parsed = splitService.split(content, Type.PARAGRAPH);
        for (String element : parsed) {
            Composite paragraph = new Composite(Type.PARAGRAPH);
            ((Composite) component).add(paragraph);
            if (root != null) {
                root.handleSplit(paragraph, element);
            }
        }
    }
}
