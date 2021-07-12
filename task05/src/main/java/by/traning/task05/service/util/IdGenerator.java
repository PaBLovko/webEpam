package by.traning.task05.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerator {
    private static int id;
    public static int idGenerator(){
        return id++;
    }
}
