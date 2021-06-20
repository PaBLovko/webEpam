package by.traning.task04.bean;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * The class that has the necessary data to work with car
 */
@Data
public class Car {

    /**
     * machine model
     */
    @NonNull
    private String modelName;

    /**
     * machine engine
     */
    @NonNull
    private Engine engine;

    /**
     * car wheels
     */
    @NonNull
    private List<Wheel> wheels;

    /**
     * tank condition of the machine
     */
    private boolean isFuel;
}
