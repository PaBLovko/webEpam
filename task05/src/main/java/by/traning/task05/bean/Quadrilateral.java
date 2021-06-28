package by.traning.task05.bean;

import lombok.Data;
import lombok.NonNull;

@Data
public class Quadrilateral {
    @NonNull
    private Point pointA;
    @NonNull
    private Point pointB;
    @NonNull
    private Point pointC;
    @NonNull
    private Point pointD;
    @NonNull
    private Integer quadrilateralId;

    @NonNull
    @Data
    public class Point{
        private int coordinateX;
        private int coordinateY;
    }
}
