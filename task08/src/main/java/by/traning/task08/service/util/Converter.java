package by.traning.task08.service.util;

public interface Converter<What , ToWhat> {
    public ToWhat convert(What object);
}
