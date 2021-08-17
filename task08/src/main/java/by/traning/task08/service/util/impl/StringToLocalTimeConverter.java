package by.traning.task08.service.util.impl;

import by.traning.task08.service.util.Converter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringToLocalTimeConverter implements Converter<String , LocalTime> {

    private static class StringToLocalTimeConverterHolder {
        private static final StringToLocalTimeConverter INSTANCE = new StringToLocalTimeConverter();
    }

    public static StringToLocalTimeConverter getInstance() {
        return StringToLocalTimeConverterHolder.INSTANCE;
    }

    @Override
    public LocalTime convert(String object) {
        String[] hhmmss = object.trim().split(":");
        return LocalTime.of(Integer.parseInt(hhmmss[0]) , Integer.parseInt(hhmmss[1]) , Integer.parseInt(hhmmss[2]));
    }
}
