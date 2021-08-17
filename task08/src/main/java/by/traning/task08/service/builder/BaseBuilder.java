package by.traning.task08.service.builder;

import by.traning.task08.bean.Gem;
import by.traning.task08.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBuilder {
    protected List<Gem> gemList = new ArrayList<>();

    public List<Gem> getGemList() {
        return gemList;
    }

    public abstract void buildGemList() throws ServiceException;
}
