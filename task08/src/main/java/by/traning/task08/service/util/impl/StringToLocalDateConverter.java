package by.traning.task08.service.util.impl;

import by.traning.task08.service.util.Converter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringToLocalDateConverter implements Converter<String , LocalDate> {

    private static class StringToLocalDateConverterHolder {
        private static final StringToLocalDateConverter INSTANCE = new StringToLocalDateConverter();
    }

    public static StringToLocalDateConverter getInstance() {
        return StringToLocalDateConverterHolder.INSTANCE;
    }

    @Override
    public LocalDate convert(String object) {
        String[] yyyymmdd = object.trim().split("-");
        return LocalDate.of(Integer.parseInt(yyyymmdd[0]) , Integer.parseInt(yyyymmdd[1]) , Integer.parseInt(yyyymmdd[2]));
    }
}