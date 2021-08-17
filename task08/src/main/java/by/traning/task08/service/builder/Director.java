package by.traning.task08.service.builder;

import by.traning.task08.bean.Gem;
import by.traning.task08.service.exception.ServiceException;

import java.util.List;

public class Director {
    public static List<Gem> createGemList(BaseBuilder builder) throws ServiceException {
        builder.buildGemList();
        return builder.getGemList();
    }
}
