package com.softserve.edu.sporthubujp.exception;

public class ImageException extends RuntimeException{
    private static final String IMAGE_EXCEPTION = "Unknown image error occurred";

    public ImageException(String message) {
        super(message.isEmpty() ? IMAGE_EXCEPTION : message);
    }

    public ImageException() {
        super(IMAGE_EXCEPTION);
    }
}
