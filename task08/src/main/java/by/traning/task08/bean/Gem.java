package by.traning.task08.bean;

import by.traning.task08.service.util.impl.StringToLocalDateConverter;
import by.traning.task08.service.util.impl.StringToLocalTimeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class Gem {
    private String name;
    private Preciousnes preciousnes;
    private String origin;
    private VisualParameter visualParameter;
    private double value;
    private LocalDate date;
    private LocalTime time;

    public Gem(String name, String preciousnes, String origin, String color , double transparency , int edgesCount, double value, String date , String time) {
        this.name = name;
        this.preciousnes = Preciousnes.valueOf(preciousnes);
        this.origin = origin;
        this.visualParameter = new VisualParameter(color , transparency , edgesCount);
        this.value = value;
        this.date = StringToLocalDateConverter.getInstance().convert(date);
        this.time = StringToLocalTimeConverter.getInstance().convert(time);
    }
}
