package by.traning.task05.bean;

import by.traning.task05.service.util.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
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

    public Quadrilateral(@NonNull Point pointA, @NonNull Point pointB, @NonNull Point pointC, @NonNull Point pointD){
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
    }

    public void setGeneratedQuadrilateralId(){
        this.quadrilateralId = IdGenerator.idGenerator();
    }

    @NonNull
    @Data
    @AllArgsConstructor
    public class Point{
        private int coordinateX;
        private int coordinateY;
    }
}
