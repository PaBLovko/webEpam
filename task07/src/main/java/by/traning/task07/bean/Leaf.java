package by.traning.task07.bean;

import lombok.Data;

/**
 * an indivisible element of the tree structure
 */
@Data
public class Leaf implements Component {
    private final Type TYPE = Type.CHARACTER;
    private char symbol;

    @Override
    public String collect() {
        return String.valueOf(symbol);
    }
}
