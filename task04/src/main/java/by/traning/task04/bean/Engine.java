package by.traning.task04.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The class that has the necessary data to work with engine
 */
@AllArgsConstructor
@Data
public class Engine {

    /**
     * engine power
     */
    private int power;

    /**
     * engine volume
     */
    private double volume;
}

