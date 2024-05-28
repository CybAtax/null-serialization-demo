package com.test.demo;

public class DemoWrapper {

    public static final String UNDEFINED_PRIMITIVE = "#UNDEFINED";
    protected final String value;

    public DemoWrapper(final String value) {
        this.value = value;
    }

    public boolean isUndefined() {
        return UNDEFINED_PRIMITIVE.equals(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
