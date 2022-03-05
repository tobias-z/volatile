package com.tobiasz.applicationserver.setup;

public class OneInitializer extends Initializer {

    private String message;

    @Override
    public void init() {
        this.message = "bob";
    }

    public String getMessage() {
        return this.message;
    }
}
