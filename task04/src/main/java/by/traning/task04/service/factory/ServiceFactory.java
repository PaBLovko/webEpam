package by.traning.task04.service.factory;

import by.traning.task04.service.CarService;
import by.traning.task04.service.impl.CarServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ServiceFactory {
    @Getter
    private final CarService carService = new CarServiceImpl();
}

