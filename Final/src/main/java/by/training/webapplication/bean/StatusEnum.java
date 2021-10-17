package by.training.webapplication.bean;

public enum StatusEnum {
    READY("ready"),
    NOT_READY("not ready"),
    DELIVERED("delivered"),
    NOT_DELIVERED("not delivered");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
