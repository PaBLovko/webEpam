package by.traning.task07.service;

import by.traning.task07.bean.Type;

import java.util.List;

public interface SplitService {
    List<String> split(String element, Type dest);
}
