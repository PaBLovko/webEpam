package by.traning.task07.bean;

import lombok.Data;

@Data
public class Leaf implements Component {
    private static final Type TYPE = Type.CHARACTER;
    private char symbol;

    @Override
    public String collect() {
        return String.valueOf(symbol);
    }
}
